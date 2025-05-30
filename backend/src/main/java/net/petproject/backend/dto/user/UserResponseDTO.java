package net.petproject.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.petproject.backend.dto.cart.CartResponseDTO;
import net.petproject.backend.dto.role.RoleDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;

import java.time.LocalDateTime;
import java.util.Set;

// Общие DTO для User
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleDTO role; // Вложенный DTO для роли
    private CartResponseDTO cart; // Вложенный DTO для корзины (может быть null)
    private Set<AddressResponseDTO> addresses; // Список адресов
}
