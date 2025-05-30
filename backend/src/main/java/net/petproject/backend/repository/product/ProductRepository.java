package net.petproject.backend.repository.product;

import net.petproject.backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    List<Product> findByStockQuantityLessThan(Integer quantity); // Для уведомлений о низком запасе
}
