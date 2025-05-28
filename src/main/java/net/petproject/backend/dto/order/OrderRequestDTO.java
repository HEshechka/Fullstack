package net.petproject.backend.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.petproject.backend.dto.order.order_item.OrderItemRequestDTO;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequestDTO {
    private Long billingAddressId;
    private Long shippingAddressId;
    private Long statusId; // ID статуса, например, "PENDING"
    private String paymentMethod;
    // totalAmount будет рассчитан на сервере
    private Set<OrderItemRequestDTO> items; // Позиции для заказа
}
