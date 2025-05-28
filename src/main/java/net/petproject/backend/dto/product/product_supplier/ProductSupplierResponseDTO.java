package net.petproject.backend.dto.product.product_supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSupplierResponseDTO {
    private Long id;
    private Long productId;
    private String productName; // Удобно для ответа
    private Long supplierId;
    private String supplierName; // Удобно для ответа
    private BigDecimal supplyPrice;
    private Integer minOrderQuantity;
    private LocalDateTime addedDate;
}
