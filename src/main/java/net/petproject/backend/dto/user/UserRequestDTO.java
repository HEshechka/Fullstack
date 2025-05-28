package net.petproject.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.petproject.backend.dto.user.address.AddressRequestDTO;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    private String email;
    private String password; // Пароль для регистрации или изменения
    private String firstName;
    private String lastName;
    private Long roleId; // Для установки роли пользователя
    // Для регистрации или обновления - адреса могут быть добавлены отдельно или как часть запроса
    private Set<AddressRequestDTO> addresses;
}
