package net.petproject.backend.mapper.cart;

import net.petproject.backend.dto.cart.CartResponseDTO;
import net.petproject.backend.model.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring", uses = {CartItemMapper.class}) // Используем CartItemMapper
public interface CartMapper {
    @Mapping(target = "userId", source = "cart.user.id")
    @Mapping(target = "items", source = "cart.cartItems")
    CartResponseDTO toDto(Cart cart);

    // Для создания корзины, user устанавливается в сервисе
    // @Mapping(target = "user", ignore = true)
    // @Mapping(target = "id", ignore = true)
    // @Mapping(target = "cartItems", ignore = true)
    // Cart toEntity(CartRequestDTO cartRequestDTO); // Если такой DTO используется для создания
}
