package net.petproject.backend.mapper.user;

import net.petproject.backend.dto.user.UserRequestDTO;
import net.petproject.backend.dto.user.UserResponseDTO;
import net.petproject.backend.mapper.cart.CartMapper;
import net.petproject.backend.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {RoleMapper.class, CartMapper.class, AddressMapper.class})
public interface UserMapper {
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "cart", source = "user.cart")
    @Mapping(target = "addresses", source = "user.addresses")
    UserResponseDTO toDto(User user);

    // При маппинге из DTO в сущность User
    @Mapping(target = "id", ignore = true) // ID генерируется БД
    @Mapping(target = "passwordHash", ignore = true) // Пароль хешируется в сервисе
    @Mapping(target = "createdAt", ignore = true) // Устанавливается через @CreationTimestamp
    @Mapping(target = "updatedAt", ignore = true) // Устанавливается через @UpdateTimestamp
    @Mapping(target = "role", ignore = true) // Роль устанавливается в сервисе по roleId
    @Mapping(target = "cart", ignore = true) // Корзина создается или обновляется в сервисе
    @Mapping(target = "orders", ignore = true) // Заказы управляются отдельно
    @Mapping(target = "addresses", ignore = true) // Адреса управляются отдельно
    User toEntity(UserRequestDTO userRequestDTO);
}
