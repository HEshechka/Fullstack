package net.petproject.backend.mapper.supplier;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.supplier.SupplierRequestDTO;
import net.petproject.backend.dto.supplier.SupplierResponseDTO;
import net.petproject.backend.model.supplier.Supplier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SupplierResponseDTO toDto(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierResponseDTO.SupplierResponseDTOBuilder supplierResponseDTO = SupplierResponseDTO.builder();

        supplierResponseDTO.id( supplier.getId() );
        supplierResponseDTO.supplierName( supplier.getSupplierName() );
        supplierResponseDTO.contactPerson( supplier.getContactPerson() );
        supplierResponseDTO.phoneNumber( supplier.getPhoneNumber() );
        supplierResponseDTO.email( supplier.getEmail() );
        supplierResponseDTO.createdAt( supplier.getCreatedAt() );

        return supplierResponseDTO.build();
    }

    @Override
    public Supplier toEntity(SupplierRequestDTO supplierRequestDTO) {
        if ( supplierRequestDTO == null ) {
            return null;
        }

        Supplier.SupplierBuilder supplier = Supplier.builder();

        supplier.supplierName( supplierRequestDTO.getSupplierName() );
        supplier.contactPerson( supplierRequestDTO.getContactPerson() );
        supplier.phoneNumber( supplierRequestDTO.getPhoneNumber() );
        supplier.email( supplierRequestDTO.getEmail() );

        return supplier.build();
    }
}
