package net.petproject.backend.mapper.user;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import net.petproject.backend.dto.user.UserRequestDTO;
import net.petproject.backend.dto.user.UserResponseDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;
import net.petproject.backend.mapper.cart.CartMapper;
import net.petproject.backend.model.user.Address;
import net.petproject.backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public UserResponseDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.role( roleMapper.toDto( user.getRole() ) );
        userResponseDTO.cart( cartMapper.toDto( user.getCart() ) );
        userResponseDTO.addresses( addressSetToAddressResponseDTOSet( user.getAddresses() ) );
        userResponseDTO.id( user.getId() );
        userResponseDTO.email( user.getEmail() );
        userResponseDTO.firstName( user.getFirstName() );
        userResponseDTO.lastName( user.getLastName() );
        userResponseDTO.createdAt( user.getCreatedAt() );
        userResponseDTO.updatedAt( user.getUpdatedAt() );

        return userResponseDTO.build();
    }

    @Override
    public User toEntity(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userRequestDTO.getEmail() );
        user.firstName( userRequestDTO.getFirstName() );
        user.lastName( userRequestDTO.getLastName() );

        return user.build();
    }

    protected Set<AddressResponseDTO> addressSetToAddressResponseDTOSet(Set<Address> set) {
        if ( set == null ) {
            return null;
        }

        Set<AddressResponseDTO> set1 = new LinkedHashSet<AddressResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Address address : set ) {
            set1.add( addressMapper.toDto( address ) );
        }

        return set1;
    }
}
