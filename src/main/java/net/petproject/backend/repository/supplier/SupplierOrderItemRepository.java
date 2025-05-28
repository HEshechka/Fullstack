package net.petproject.backend.repository.supplier;

import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.supplier.SupplierOrder;
import net.petproject.backend.model.supplier.SupplierOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierOrderItemRepository extends JpaRepository<SupplierOrderItem, Long> {
    List<SupplierOrderItem> findBySupplierOrder(SupplierOrder supplierOrder);
    Optional<SupplierOrderItem> findBySupplierOrderAndProduct(SupplierOrder supplierOrder, Product product);
}
