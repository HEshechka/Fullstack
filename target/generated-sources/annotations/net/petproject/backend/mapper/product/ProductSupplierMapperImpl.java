package net.petproject.backend.mapper.product;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierRequestDTO;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierResponseDTO;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.ProductSupplier;
import net.petproject.backend.model.supplier.Supplier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductSupplierMapperImpl implements ProductSupplierMapper {

    @Override
    public ProductSupplierResponseDTO toDto(ProductSupplier productSupplier) {
        if ( productSupplier == null ) {
            return null;
        }

        ProductSupplierResponseDTO.ProductSupplierResponseDTOBuilder productSupplierResponseDTO = ProductSupplierResponseDTO.builder();

        productSupplierResponseDTO.productId( productSupplierProductId( productSupplier ) );
        productSupplierResponseDTO.productName( productSupplierProductProductName( productSupplier ) );
        productSupplierResponseDTO.supplierId( productSupplierSupplierId( productSupplier ) );
        productSupplierResponseDTO.supplierName( productSupplierSupplierSupplierName( productSupplier ) );
        productSupplierResponseDTO.id( productSupplier.getId() );
        productSupplierResponseDTO.supplyPrice( productSupplier.getSupplyPrice() );
        productSupplierResponseDTO.minOrderQuantity( productSupplier.getMinOrderQuantity() );
        productSupplierResponseDTO.addedDate( productSupplier.getAddedDate() );

        return productSupplierResponseDTO.build();
    }

    @Override
    public ProductSupplier toEntity(ProductSupplierRequestDTO productSupplierRequestDTO) {
        if ( productSupplierRequestDTO == null ) {
            return null;
        }

        ProductSupplier.ProductSupplierBuilder productSupplier = ProductSupplier.builder();

        productSupplier.supplyPrice( productSupplierRequestDTO.getSupplyPrice() );
        productSupplier.minOrderQuantity( productSupplierRequestDTO.getMinOrderQuantity() );

        return productSupplier.build();
    }

    private Long productSupplierProductId(ProductSupplier productSupplier) {
        if ( productSupplier == null ) {
            return null;
        }
        Product product = productSupplier.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productSupplierProductProductName(ProductSupplier productSupplier) {
        if ( productSupplier == null ) {
            return null;
        }
        Product product = productSupplier.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }

    private Long productSupplierSupplierId(ProductSupplier productSupplier) {
        if ( productSupplier == null ) {
            return null;
        }
        Supplier supplier = productSupplier.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productSupplierSupplierSupplierName(ProductSupplier productSupplier) {
        if ( productSupplier == null ) {
            return null;
        }
        Supplier supplier = productSupplier.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String supplierName = supplier.getSupplierName();
        if ( supplierName == null ) {
            return null;
        }
        return supplierName;
    }
}
