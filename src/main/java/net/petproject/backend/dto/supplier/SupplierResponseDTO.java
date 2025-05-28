package net.petproject.backend.dto.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierResponseDTO {
    private Long id;
    private String supplierName;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdAt;
    // Можно добавить список поставляемых продуктов, если нужно
    // private Set<ProductResponseDTO> suppliedProducts;
}
