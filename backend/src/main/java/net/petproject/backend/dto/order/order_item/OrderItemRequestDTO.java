package net.petproject.backend.dto.order.order_item;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;
    // unitPrice будет взят из Product модели на сервере
}
