package net.petproject.backend.repository.product;

import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.ProductSupplier;
import net.petproject.backend.model.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {
    List<ProductSupplier> findByProduct(Product product);
    List<ProductSupplier> findBySupplier(Supplier supplier);
    Optional<ProductSupplier> findByProductAndSupplier(Product product, Supplier supplier);
}
