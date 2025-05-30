package net.petproject.backend.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

// Используем Lombok для сокращения кода (геттеры, сеттеры, конструкторы и т.д.)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Полезно для создания объектов
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    // Отношение OneToMany с User (одна роль может быть у многих пользователей)
    //MappedBy указывает на поле "role" в классе User
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();
}