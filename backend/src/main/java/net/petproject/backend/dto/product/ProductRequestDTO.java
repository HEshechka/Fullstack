package net.petproject.backend.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequestDTO {
    private String productName;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    // Для создания продукта, поставщики могут быть добавлены позже или через отдельный API
}
