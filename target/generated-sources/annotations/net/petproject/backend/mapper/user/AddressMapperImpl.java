package net.petproject.backend.mapper.user;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.user.address.AddressRequestDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;
import net.petproject.backend.model.user.Address;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressResponseDTO toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponseDTO.AddressResponseDTOBuilder addressResponseDTO = AddressResponseDTO.builder();

        addressResponseDTO.id( address.getId() );
        addressResponseDTO.street( address.getStreet() );
        addressResponseDTO.city( address.getCity() );
        addressResponseDTO.state( address.getState() );
        addressResponseDTO.zipCode( address.getZipCode() );
        addressResponseDTO.country( address.getCountry() );
        addressResponseDTO.addressType( address.getAddressType() );
        addressResponseDTO.isDefault( address.getIsDefault() );

        return addressResponseDTO.build();
    }

    @Override
    public Address toEntity(AddressRequestDTO addressRequestDTO) {
        if ( addressRequestDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.street( addressRequestDTO.getStreet() );
        address.city( addressRequestDTO.getCity() );
        address.state( addressRequestDTO.getState() );
        address.zipCode( addressRequestDTO.getZipCode() );
        address.country( addressRequestDTO.getCountry() );
        address.addressType( addressRequestDTO.getAddressType() );
        address.isDefault( addressRequestDTO.getIsDefault() );

        return address.build();
    }
}
