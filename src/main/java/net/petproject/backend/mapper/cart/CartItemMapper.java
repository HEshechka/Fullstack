package net.petproject.backend.mapper.cart;

import net.petproject.backend.dto.cart.CartItemResponseDTO;
import net.petproject.backend.mapper.product.ProductMapper;
import net.petproject.backend.model.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // Используем ProductMapper для вложенных объектов
public interface CartItemMapper {
    @Mapping(target = "cartId", source = "cartItem.cart.id")
    @Mapping(target = "productId", source = "cartItem.product.id")
    @Mapping(target = "productName", source = "cartItem.product.productName")
    CartItemResponseDTO toDto(CartItem cartItem);

    // Request DTO to Entity mapping for CartItem (usually done in service layer for complex logic)
    // @Mapping(target = "cart", ignore = true) // Cart and Product will be set in service
    // @Mapping(target = "product", ignore = true)
    // @Mapping(target = "id", ignore = true)
    // CartItem toEntity(CartItemRequestDTO cartItemRequestDTO);
}
