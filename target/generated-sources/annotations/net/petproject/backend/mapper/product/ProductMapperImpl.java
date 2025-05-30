package net.petproject.backend.mapper.product;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.product.ProductRequestDTO;
import net.petproject.backend.dto.product.ProductResponseDTO;
import net.petproject.backend.model.product.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO.ProductResponseDTOBuilder productResponseDTO = ProductResponseDTO.builder();

        productResponseDTO.id( product.getId() );
        productResponseDTO.productName( product.getProductName() );
        productResponseDTO.description( product.getDescription() );
        productResponseDTO.price( product.getPrice() );
        productResponseDTO.stockQuantity( product.getStockQuantity() );
        productResponseDTO.createdAt( product.getCreatedAt() );

        return productResponseDTO.build();
    }

    @Override
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productName( productRequestDTO.getProductName() );
        product.description( productRequestDTO.getDescription() );
        product.price( productRequestDTO.getPrice() );
        product.stockQuantity( productRequestDTO.getStockQuantity() );

        return product.build();
    }
}
