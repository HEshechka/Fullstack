package net.petproject.backend.mapper.supplier;

import net.petproject.backend.dto.supplier.SupplierRequestDTO;
import net.petproject.backend.dto.supplier.SupplierResponseDTO;
import net.petproject.backend.model.supplier.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResponseDTO toDto(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "productSuppliers", ignore = true)
    @Mapping(target = "supplierOrders", ignore = true)
    Supplier toEntity(SupplierRequestDTO supplierRequestDTO);
}
