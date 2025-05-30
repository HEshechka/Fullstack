package net.petproject.backend.mapper.supplier;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderItemRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderItemResponseDTO;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.supplier.SupplierOrder;
import net.petproject.backend.model.supplier.SupplierOrderItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class SupplierOrderItemMapperImpl implements SupplierOrderItemMapper {

    @Override
    public SupplierOrderItemResponseDTO toDto(SupplierOrderItem supplierOrderItem) {
        if ( supplierOrderItem == null ) {
            return null;
        }

        SupplierOrderItemResponseDTO.SupplierOrderItemResponseDTOBuilder supplierOrderItemResponseDTO = SupplierOrderItemResponseDTO.builder();

        supplierOrderItemResponseDTO.supplierOrderId( supplierOrderItemSupplierOrderId( supplierOrderItem ) );
        supplierOrderItemResponseDTO.productId( supplierOrderItemProductId( supplierOrderItem ) );
        supplierOrderItemResponseDTO.productName( supplierOrderItemProductProductName( supplierOrderItem ) );
        supplierOrderItemResponseDTO.id( supplierOrderItem.getId() );
        supplierOrderItemResponseDTO.quantity( supplierOrderItem.getQuantity() );
        supplierOrderItemResponseDTO.unitPrice( supplierOrderItem.getUnitPrice() );

        return supplierOrderItemResponseDTO.build();
    }

    @Override
    public SupplierOrderItem toEntity(SupplierOrderItemRequestDTO supplierOrderItemRequestDTO) {
        if ( supplierOrderItemRequestDTO == null ) {
            return null;
        }

        SupplierOrderItem.SupplierOrderItemBuilder supplierOrderItem = SupplierOrderItem.builder();

        supplierOrderItem.quantity( supplierOrderItemRequestDTO.getQuantity() );
        supplierOrderItem.unitPrice( supplierOrderItemRequestDTO.getUnitPrice() );

        return supplierOrderItem.build();
    }

    private Long supplierOrderItemSupplierOrderId(SupplierOrderItem supplierOrderItem) {
        if ( supplierOrderItem == null ) {
            return null;
        }
        SupplierOrder supplierOrder = supplierOrderItem.getSupplierOrder();
        if ( supplierOrder == null ) {
            return null;
        }
        Long id = supplierOrder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long supplierOrderItemProductId(SupplierOrderItem supplierOrderItem) {
        if ( supplierOrderItem == null ) {
            return null;
        }
        Product product = supplierOrderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String supplierOrderItemProductProductName(SupplierOrderItem supplierOrderItem) {
        if ( supplierOrderItem == null ) {
            return null;
        }
        Product product = supplierOrderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }
}
