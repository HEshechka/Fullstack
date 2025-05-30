package net.petproject.backend.repository.util;

import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.StockNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockNotificationRepository extends JpaRepository<StockNotification, Long> {
    List<StockNotification> findByProduct(Product product);
    List<StockNotification> findByIsNotifiedFalse(); // Найти непрочитанные уведомления
}
