package net.petproject.backend.service.user;

import net.petproject.backend.dto.user.UserRequestDTO;
import net.petproject.backend.dto.user.UserResponseDTO;
import net.petproject.backend.mapper.user.UserMapper;
import net.petproject.backend.model.cart.Cart;
import net.petproject.backend.model.user.Role;
import net.petproject.backend.model.user.User;
import net.petproject.backend.repository.cart.CartRepository;
import net.petproject.backend.repository.user.RoleRepository;
import net.petproject.backend.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, CartRepository cartRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cartRepository = cartRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO registerNewUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        User user = userMapper.toEntity(userRequestDTO);

        Role defaultRole = roleRepository.findByRoleName("CUSTOMER") // Или можно сделать DTO для роли
                .orElseThrow(() -> new RuntimeException("Default CUSTOMER role not found. Please create it."));
        user.setRole(defaultRole);

        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        // Создать корзину для нового пользователя
        Cart cart = Cart.builder()
                .user(savedUser)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        cartRepository.save(cart);
        savedUser.setCart(cart); // Установить корзину для пользователя

        return userMapper.toDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        if (userRequestDTO.getFirstName() != null) {
            existingUser.setFirstName(userRequestDTO.getFirstName());
        }
        if (userRequestDTO.getLastName() != null) {
            existingUser.setLastName(userRequestDTO.getLastName());
        }
        if (userRequestDTO.getEmail() != null && !existingUser.getEmail().equals(userRequestDTO.getEmail())) {
            if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
                throw new IllegalArgumentException("New email already in use.");
            }
            existingUser.setEmail(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
        }
        if (userRequestDTO.getRoleId() != null) {
            Role newRole = roleRepository.findById(userRequestDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userRequestDTO.getRoleId()));
            existingUser.setRole(newRole);
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
