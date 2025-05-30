package net.petproject.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.petproject.backend.model.supplier.Supplier;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_suppliers")
public class ProductSupplier { // Связующая таблица для Many-to-Many между Product и Supplier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_supplier_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "supply_price", precision = 10, scale = 2)
    private BigDecimal supplyPrice;

    @Column(name = "min_order_quantity")
    private Integer minOrderQuantity;

    @CreationTimestamp
    @Column(name = "added_date", nullable = false, updatable = false)
    private LocalDateTime addedDate;
}
