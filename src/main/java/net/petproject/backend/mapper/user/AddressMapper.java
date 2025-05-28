package net.petproject.backend.mapper.user;

import net.petproject.backend.dto.user.address.AddressRequestDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;
import net.petproject.backend.model.user.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponseDTO toDto(Address address);

    // При маппинге из DTO в сущность, user не будет маппироваться напрямую
    // Он будет установлен в сервисе
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true) // ID генерируется БД
    Address toEntity(AddressRequestDTO addressRequestDTO);
}
