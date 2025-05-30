package net.petproject.backend.mapper.cart;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.cart.CartItemResponseDTO;
import net.petproject.backend.model.cart.Cart;
import net.petproject.backend.model.cart.CartItem;
import net.petproject.backend.model.product.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemResponseDTO toDto(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponseDTO.CartItemResponseDTOBuilder cartItemResponseDTO = CartItemResponseDTO.builder();

        cartItemResponseDTO.cartId( cartItemCartId( cartItem ) );
        cartItemResponseDTO.productId( cartItemProductId( cartItem ) );
        cartItemResponseDTO.productName( cartItemProductProductName( cartItem ) );
        cartItemResponseDTO.id( cartItem.getId() );
        cartItemResponseDTO.quantity( cartItem.getQuantity() );
        cartItemResponseDTO.addedAt( cartItem.getAddedAt() );

        return cartItemResponseDTO.build();
    }

    private Long cartItemCartId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Cart cart = cartItem.getCart();
        if ( cart == null ) {
            return null;
        }
        Long id = cart.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long cartItemProductId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String cartItemProductProductName(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Product product = cartItem.getProduct();
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
