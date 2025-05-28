package net.petproject.backend.mapper.product;

import net.petproject.backend.dto.product.product_supplier.ProductSupplierRequestDTO;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierResponseDTO;
import net.petproject.backend.mapper.supplier.SupplierMapper;
import net.petproject.backend.model.product.ProductSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ProductMapper.class, SupplierMapper.class})
public interface ProductSupplierMapper {
    @Mapping(target = "productId", source = "productSupplier.product.id")
    @Mapping(target = "productName", source = "productSupplier.product.productName")
    @Mapping(target = "supplierId", source = "productSupplier.supplier.id")
    @Mapping(target = "supplierName", source = "productSupplier.supplier.supplierName")
    ProductSupplierResponseDTO toDto(ProductSupplier productSupplier);

    @Mapping(target = "product", ignore = true) // Product и Supplier будут установлены в сервисе
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addedDate", ignore = true) // Устанавливается через @CreationTimestamp
    ProductSupplier toEntity(ProductSupplierRequestDTO productSupplierRequestDTO);
}
