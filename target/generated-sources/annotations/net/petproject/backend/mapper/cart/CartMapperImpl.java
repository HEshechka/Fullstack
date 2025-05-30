package net.petproject.backend.mapper.cart;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import net.petproject.backend.dto.cart.CartItemResponseDTO;
import net.petproject.backend.dto.cart.CartResponseDTO;
import net.petproject.backend.model.cart.Cart;
import net.petproject.backend.model.cart.CartItem;
import net.petproject.backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartResponseDTO toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponseDTO.CartResponseDTOBuilder cartResponseDTO = CartResponseDTO.builder();

        cartResponseDTO.userId( cartUserId( cart ) );
        cartResponseDTO.items( cartItemSetToCartItemResponseDTOSet( cart.getCartItems() ) );
        cartResponseDTO.id( cart.getId() );
        cartResponseDTO.createdAt( cart.getCreatedAt() );
        cartResponseDTO.updatedAt( cart.getUpdatedAt() );

        return cartResponseDTO.build();
    }

    private Long cartUserId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<CartItemResponseDTO> cartItemSetToCartItemResponseDTOSet(Set<CartItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartItemResponseDTO> set1 = new LinkedHashSet<CartItemResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartItem cartItem : set ) {
            set1.add( cartItemMapper.toDto( cartItem ) );
        }

        return set1;
    }
}
