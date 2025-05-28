package net.petproject.backend.dto.supplier.supplier_order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierOrderRequestDTO {
    private Long supplierId;
    // orderDate и totalAmount могут быть сгенерированы на бэкенде
    private String status;
    private Set<SupplierOrderItemRequestDTO> items;
}
