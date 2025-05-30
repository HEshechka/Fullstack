package net.petproject.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stock_notifications")
public class StockNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "threshold", nullable = false) // Порог запаса, при котором генерируется уведомление
    private Integer threshold;

    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notificationDate;

    @Column(name = "is_notified", nullable = false)
    private Boolean isNotified = false; // Флаг, было ли отправлено уведомление
}
