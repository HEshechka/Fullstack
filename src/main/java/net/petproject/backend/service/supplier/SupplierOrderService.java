package net.petproject.backend.service.supplier;

import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderResponseDTO;
import net.petproject.backend.mapper.supplier.SupplierOrderItemMapper;
import net.petproject.backend.mapper.supplier.SupplierOrderMapper;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.supplier.Supplier;
import net.petproject.backend.model.supplier.SupplierOrder;
import net.petproject.backend.model.supplier.SupplierOrderItem;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.supplier.SupplierOrderRepository;
import net.petproject.backend.repository.supplier.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierOrderService {

    private final SupplierOrderRepository supplierOrderRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final SupplierOrderMapper supplierOrderMapper;
    private final SupplierOrderItemMapper supplierOrderItemMapper;

    public SupplierOrderService(SupplierOrderRepository supplierOrderRepository, SupplierRepository supplierRepository, ProductRepository productRepository, SupplierOrderMapper supplierOrderMapper, SupplierOrderItemMapper supplierOrderItemMapper) {
        this.supplierOrderRepository = supplierOrderRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.supplierOrderMapper = supplierOrderMapper;
        this.supplierOrderItemMapper = supplierOrderItemMapper;
    }

    @Transactional
    public SupplierOrderResponseDTO createSupplierOrder(SupplierOrderRequestDTO supplierOrderRequestDTO) {
        Supplier supplier = supplierRepository.findById(supplierOrderRequestDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierOrderRequestDTO.getSupplierId()));

        SupplierOrder supplierOrder = supplierOrderMapper.toEntity(supplierOrderRequestDTO);
        supplierOrder.setSupplier(supplier);
        supplierOrder.setOrderDate(LocalDateTime.now());
        supplierOrder.setStatus(supplierOrderRequestDTO.getStatus() != null ? supplierOrderRequestDTO.getStatus() : "PENDING");

        Set<SupplierOrderItem> orderItems = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (var itemDTO : supplierOrderRequestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

            SupplierOrderItem orderItem = supplierOrderItemMapper.toEntity(itemDTO);
            orderItem.setSupplierOrder(supplierOrder);
            orderItem.setProduct(product);
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
        }

        supplierOrder.setSupplierOrderItems(orderItems);
        supplierOrder.setTotalAmount(totalAmount);

        SupplierOrder savedSupplierOrder = supplierOrderRepository.save(supplierOrder);
        return supplierOrderMapper.toDto(savedSupplierOrder);
    }

    @Transactional(readOnly = true)
    public SupplierOrderResponseDTO getSupplierOrderById(Long id) {
        SupplierOrder supplierOrder = supplierOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Order not found with ID: " + id));
        return supplierOrderMapper.toDto(supplierOrder);
    }

    @Transactional(readOnly = true)
    public List<SupplierOrderResponseDTO> getAllSupplierOrders() {
        return supplierOrderRepository.findAll().stream()
                .map(supplierOrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SupplierOrderResponseDTO updateSupplierOrderStatus(Long id, String newStatus) {
        SupplierOrder existingOrder = supplierOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Order not found with ID: " + id));
        existingOrder.setStatus(newStatus);
        SupplierOrder updatedOrder = supplierOrderRepository.save(existingOrder);
        return supplierOrderMapper.toDto(updatedOrder);
    }

    @Transactional
    public void deleteSupplierOrder(Long id) {
        supplierOrderRepository.deleteById(id);
    }

    // Метод для "приемки" товаров по заказу поставщика
    @Transactional
    public SupplierOrderResponseDTO receiveSupplierOrder(Long orderId) {
        SupplierOrder supplierOrder = supplierOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Supplier Order not found with ID: " + orderId));

        if (!"SHIPPED".equals(supplierOrder.getStatus())) { // Пример логики
            throw new IllegalStateException("Supplier order must be in 'SHIPPED' status to be received.");
        }

        for (SupplierOrderItem item : supplierOrder.getSupplierOrderItems()) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            productRepository.save(product);
        }

        supplierOrder.setStatus("RECEIVED");
        return supplierOrderMapper.toDto(supplierOrderRepository.save(supplierOrder));
    }
}
