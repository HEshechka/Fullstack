package net.petproject.backend.service.order;

import net.petproject.backend.dto.order.CreateOrderFromCartDTO;
import net.petproject.backend.dto.order.OrderRequestDTO;
import net.petproject.backend.dto.order.OrderResponseDTO;
import net.petproject.backend.mapper.order.OrderItemMapper;
import net.petproject.backend.mapper.order.OrderMapper;
import net.petproject.backend.model.cart.Cart;
import net.petproject.backend.model.cart.CartItem;
import net.petproject.backend.model.order.Order;
import net.petproject.backend.model.order.OrderItem;
import net.petproject.backend.model.order.OrderStatus;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.user.Address;
import net.petproject.backend.model.user.User;
import net.petproject.backend.repository.cart.CartItemRepository;
import net.petproject.backend.repository.cart.CartRepository;
import net.petproject.backend.repository.order.OrderRepository;
import net.petproject.backend.repository.order.OrderStatusRepository;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.user.AddressRepository;
import net.petproject.backend.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper; // Для маппинга позиций заказа

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository, OrderStatusRepository orderStatusRepository, ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Address billingAddress = addressRepository.findById(orderRequestDTO.getBillingAddressId())
                .orElseThrow(() -> new RuntimeException("Billing address not found."));
        Address shippingAddress = addressRepository.findById(orderRequestDTO.getShippingAddressId())
                .orElseThrow(() -> new RuntimeException("Shipping address not found."));

        OrderStatus initialStatus = orderStatusRepository.findByStatusName("PENDING")
                .orElseThrow(() -> new RuntimeException("Initial order status 'PENDING' not found."));

        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setUser(user);
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        order.setStatus(initialStatus);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentStatus("PENDING"); // Изначальный статус оплаты

        Set<OrderItem> orderItems = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (var itemDTO : orderRequestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

            if (product.getStockQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getProductName());
            }

            OrderItem orderItem = orderItemMapper.toEntity(itemDTO);
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setUnitPrice(product.getPrice()); // Цена на момент заказа
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));

            // Уменьшить количество на складе
            product.setStockQuantity(product.getStockQuantity() - itemDTO.getQuantity());
            productRepository.save(product);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderResponseDTO createOrderFromCart(CreateOrderFromCartDTO createOrderFromCartDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Cart userCart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));

        if (userCart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot create order from empty cart.");
        }

        Address billingAddress = addressRepository.findById(createOrderFromCartDTO.getBillingAddressId())
                .orElseThrow(() -> new RuntimeException("Billing address not found."));
        Address shippingAddress = addressRepository.findById(createOrderFromCartDTO.getShippingAddressId())
                .orElseThrow(() -> new RuntimeException("Shipping address not found."));

        OrderStatus initialStatus = orderStatusRepository.findByStatusName("PENDING")
                .orElseThrow(() -> new RuntimeException("Initial order status 'PENDING' not found."));

        Order order = Order.builder()
                .user(user)
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .status(initialStatus)
                .orderDate(LocalDateTime.now())
                .paymentMethod(createOrderFromCartDTO.getPaymentMethod())
                .paymentStatus("PENDING")
                .build();

        Set<OrderItem> orderItems = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : userCart.getCartItems()) {
            Product product = cartItem.getProduct();
            Integer quantity = cartItem.getQuantity();

            if (product.getStockQuantity() < quantity) {
                throw new RuntimeException("Not enough stock for product: " + product.getProductName());
            }

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .unitPrice(product.getPrice())
                    .build();
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));

            // Уменьшить количество на складе
            product.setStockQuantity(product.getStockQuantity() - quantity);
            productRepository.save(product);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Очистить корзину после создания заказа
        cartItemRepository.deleteAll(userCart.getCartItems());
        userCart.getCartItems().clear(); // Удаляем из коллекции в памяти
        cartRepository.save(userCart); // Сохраняем обновленную корзину (хотя orphanRemoval должен сработать)


        return orderMapper.toDto(savedOrder);
    }


    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        return orderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDTO updateOrderStatus(Long orderId, Long statusId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        OrderStatus newStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Order status not found with ID: " + statusId));

        existingOrder.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDto(updatedOrder);
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
