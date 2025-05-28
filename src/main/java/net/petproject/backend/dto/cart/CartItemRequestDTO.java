package net.petproject.backend.dto.cart;

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
public class CartItemRequestDTO {
    private Long productId;
    private Integer quantity;
    // cartId не нужен, он будет получен из сессии/токен
}
