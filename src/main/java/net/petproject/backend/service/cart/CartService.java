package net.petproject.backend.service.cart;

import net.petproject.backend.dto.cart.CartItemRequestDTO;
import net.petproject.backend.dto.cart.CartResponseDTO;
import net.petproject.backend.mapper.cart.CartMapper;
import net.petproject.backend.model.cart.Cart;
import net.petproject.backend.model.cart.CartItem;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.user.User;
import net.petproject.backend.repository.cart.CartItemRepository;
import net.petproject.backend.repository.cart.CartRepository;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, ProductRepository productRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
    }

    @Transactional(readOnly = true)
    public CartResponseDTO getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        return cartMapper.toDto(cart);
    }

    @Transactional
    public CartResponseDTO addProductToCart(Long userId, CartItemRequestDTO cartItemRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        Product product = productRepository.findById(cartItemRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartItemRequestDTO.getProductId()));

        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            CartItem item = existingCartItem.get();
            item.setQuantity(item.getQuantity() + cartItemRequestDTO.getQuantity());
            cartItemRepository.save(item);
        } else {
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(cartItemRequestDTO.getQuantity())
                    .addedAt(LocalDateTime.now())
                    .build();
            cartItemRepository.save(newCartItem);
            cart.getCartItems().add(newCartItem); // Добавляем в коллекцию корзины в памяти
        }
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart); // Сохраняем корзину, чтобы обновить updated_at
        return cartMapper.toDto(cart);
    }

    @Transactional
    public CartResponseDTO updateProductQuantityInCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Product not in cart."));

        if (quantity <= 0) {
            cartItemRepository.delete(existingCartItem);
            cart.getCartItems().remove(existingCartItem); // Удаляем из коллекции в памяти
        } else {
            existingCartItem.setQuantity(quantity);
            cartItemRepository.save(existingCartItem);
        }
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Product not in cart."));

        cartItemRepository.delete(existingCartItem);
        cart.getCartItems().remove(existingCartItem); // Удаляем из коллекции в памяти
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));

        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear(); // Очищаем коллекцию в памяти
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
    }
}
