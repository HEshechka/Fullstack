package net.petproject.backend.repository.supplier;

import net.petproject.backend.model.supplier.Supplier;
import net.petproject.backend.model.supplier.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
    List<SupplierOrder> findBySupplier(Supplier supplier);
    List<SupplierOrder> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
