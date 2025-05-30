package net.petproject.backend.mapper.product;

import net.petproject.backend.dto.product.ProductRequestDTO;
import net.petproject.backend.dto.product.ProductResponseDTO;
import net.petproject.backend.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "productSuppliers", ignore = true)
    @Mapping(target = "priceChanges", ignore = true)
    @Mapping(target = "stockNotifications", ignore = true)
    Product toEntity(ProductRequestDTO productRequestDTO);
}
