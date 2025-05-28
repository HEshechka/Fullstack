package net.petproject.backend.controller.user;

import jakarta.validation.Valid; // Для валидации DTO
import net.petproject.backend.dto.user.UserLoginRequestDTO;
import net.petproject.backend.dto.user.UserLoginResponseDTO;
import net.petproject.backend.dto.user.UserRequestDTO;
import net.petproject.backend.dto.user.UserResponseDTO;
import net.petproject.backend.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO newUser = userService.registerNewUser(userRequestDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Для простоты, не реализую полную аутентификацию здесь, но логика будет примерно такая:
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO loginRequestDTO) {
        // В реальном приложении здесь будет логика Spring Security:
        // - Аутентификация пользователя
        // - Генерация JWT токена
        // - Возвращение токена и базовой информации о пользователе
        UserResponseDTO user = userService.getUserByEmail(loginRequestDTO.getEmail()); // Пример
        String token = "mock_jwt_token_for_" + user.getEmail(); // Заглушка токена

        UserLoginResponseDTO response = UserLoginResponseDTO.builder()
                .token(token)
                .user(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
