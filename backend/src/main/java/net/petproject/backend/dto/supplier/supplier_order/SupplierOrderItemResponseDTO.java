package net.petproject.backend.dto.supplier.supplier_order;

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
public class SupplierOrderItemResponseDTO {
    private Long id;
    private Long supplierOrderId;
    private Long productId;
    private String productName; // Для удобства
    private Integer quantity;
    private BigDecimal unitPrice;
}