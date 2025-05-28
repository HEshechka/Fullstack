package net.petproject.backend.repository.util;

import net.petproject.backend.model.product.PriceChange;
import net.petproject.backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceChangeRepository extends JpaRepository<PriceChange, Long> {
    List<PriceChange> findByProduct(Product product);
}
