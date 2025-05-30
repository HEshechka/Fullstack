package net.petproject.backend.mapper.supplier;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderItemResponseDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderResponseDTO;
import net.petproject.backend.model.supplier.Supplier;
import net.petproject.backend.model.supplier.SupplierOrder;
import net.petproject.backend.model.supplier.SupplierOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class SupplierOrderMapperImpl implements SupplierOrderMapper {

    @Autowired
    private SupplierOrderItemMapper supplierOrderItemMapper;

    @Override
    public SupplierOrderResponseDTO toDto(SupplierOrder supplierOrder) {
        if ( supplierOrder == null ) {
            return null;
        }

        SupplierOrderResponseDTO.SupplierOrderResponseDTOBuilder supplierOrderResponseDTO = SupplierOrderResponseDTO.builder();

        supplierOrderResponseDTO.supplierId( supplierOrderSupplierId( supplierOrder ) );
        supplierOrderResponseDTO.supplierName( supplierOrderSupplierSupplierName( supplierOrder ) );
        supplierOrderResponseDTO.items( supplierOrderItemSetToSupplierOrderItemResponseDTOSet( supplierOrder.getSupplierOrderItems() ) );
        supplierOrderResponseDTO.id( supplierOrder.getId() );
        supplierOrderResponseDTO.orderDate( supplierOrder.getOrderDate() );
        supplierOrderResponseDTO.totalAmount( supplierOrder.getTotalAmount() );
        supplierOrderResponseDTO.status( supplierOrder.getStatus() );

        return supplierOrderResponseDTO.build();
    }

    @Override
    public SupplierOrder toEntity(SupplierOrderRequestDTO supplierOrderRequestDTO) {
        if ( supplierOrderRequestDTO == null ) {
            return null;
        }

        SupplierOrder.SupplierOrderBuilder supplierOrder = SupplierOrder.builder();

        supplierOrder.status( supplierOrderRequestDTO.getStatus() );

        return supplierOrder.build();
    }

    private Long supplierOrderSupplierId(SupplierOrder supplierOrder) {
        if ( supplierOrder == null ) {
            return null;
        }
        Supplier supplier = supplierOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        Long id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String supplierOrderSupplierSupplierName(SupplierOrder supplierOrder) {
        if ( supplierOrder == null ) {
            return null;
        }
        Supplier supplier = supplierOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String supplierName = supplier.getSupplierName();
        if ( supplierName == null ) {
            return null;
        }
        return supplierName;
    }

    protected Set<SupplierOrderItemResponseDTO> supplierOrderItemSetToSupplierOrderItemResponseDTOSet(Set<SupplierOrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<SupplierOrderItemResponseDTO> set1 = new LinkedHashSet<SupplierOrderItemResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( SupplierOrderItem supplierOrderItem : set ) {
            set1.add( supplierOrderItemMapper.toDto( supplierOrderItem ) );
        }

        return set1;
    }
}
