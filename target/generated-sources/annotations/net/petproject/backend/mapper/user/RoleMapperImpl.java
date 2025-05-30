package net.petproject.backend.mapper.user;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.role.RoleDTO;
import net.petproject.backend.dto.role.RoleRequestDTO;
import net.petproject.backend.model.user.Role;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( role.getId() );
        roleDTO.roleName( role.getRoleName() );

        return roleDTO.build();
    }

    @Override
    public Role toEntity(RoleRequestDTO roleRequestDTO) {
        if ( roleRequestDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.roleName( roleRequestDTO.getRoleName() );

        return role.build();
    }
}
