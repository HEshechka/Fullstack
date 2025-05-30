package net.petproject.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories") // Явно указываем имя таблицы
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    // Связь с продуктами, если есть
    // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Product> products = new HashSet<>();
}
