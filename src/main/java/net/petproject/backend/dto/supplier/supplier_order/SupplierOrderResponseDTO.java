package net.petproject.backend.dto.supplier.supplier_order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderResponseDTO {
    private Long id;
    private Long supplierId;
    private String supplierName; // Для удобства
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
    private Set<SupplierOrderItemResponseDTO> items;
}
