package net.petproject.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.petproject.backend.dto.order.delivery.DeliveryResponseDTO;
import net.petproject.backend.dto.order.order_item.OrderItemResponseDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private String customerFullName; // Для удобства
    private AddressResponseDTO billingAddress;
    private AddressResponseDTO shippingAddress;
    private OrderStatusDTO status; // Вложенный DTO
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private Set<OrderItemResponseDTO> items;
    private DeliveryResponseDTO delivery;
}
