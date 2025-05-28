package net.petproject.backend.mapper.user;

import net.petproject.backend.dto.role.RoleDTO;
import net.petproject.backend.dto.role.RoleRequestDTO;
import net.petproject.backend.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDto(Role role);

    @Mapping(target = "id", ignore = true) // ID генерируется БД
    @Mapping(target = "users", ignore = true) // Users не мапятся в DTO
    Role toEntity(RoleRequestDTO roleRequestDTO);
}
