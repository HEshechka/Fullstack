package net.petproject.backend.service.user;

import net.petproject.backend.dto.role.RoleDTO;
import net.petproject.backend.dto.role.RoleRequestDTO;
import net.petproject.backend.mapper.user.RoleMapper;
import net.petproject.backend.model.user.Role;
import net.petproject.backend.repository.user.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional
    public RoleDTO createRole(RoleRequestDTO roleRequestDTO) {
        if (roleRepository.findByRoleName(roleRequestDTO.getRoleName()).isPresent()) {
            throw new IllegalArgumentException("Role with this name already exists: " + roleRequestDTO.getRoleName());
        }
        Role role = roleMapper.toEntity(roleRequestDTO);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    @Transactional(readOnly = true)
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
        return roleMapper.toDto(role);
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoleDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        if (roleRequestDTO.getRoleName() != null && !existingRole.getRoleName().equals(roleRequestDTO.getRoleName())) {
            if (roleRepository.findByRoleName(roleRequestDTO.getRoleName()).isPresent()) {
                throw new IllegalArgumentException("Role with this name already exists: " + roleRequestDTO.getRoleName());
            }
            existingRole.setRoleName(roleRequestDTO.getRoleName());
        }

        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toDto(updatedRole);
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
