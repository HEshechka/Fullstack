package net.petproject.backend.dto.product.product_supplier;

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
public class ProductSupplierRequestDTO {
    private Long productId;
    private Long supplierId;
    private BigDecimal supplyPrice;
    private Integer minOrderQuantity;
}
