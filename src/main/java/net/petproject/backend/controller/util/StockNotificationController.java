package net.petproject.backend.controller.util;

import jakarta.validation.Valid;
import net.petproject.backend.dto.util.stock_notification.StockNotificationRequestDTO;
import net.petproject.backend.dto.util.stock_notification.StockNotificationResponseDTO;
import net.petproject.backend.service.util.StockNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-notifications")
public class StockNotificationController {

    private final StockNotificationService stockNotificationService;

    public StockNotificationController(StockNotificationService stockNotificationService) {
        this.stockNotificationService = stockNotificationService;
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity<StockNotificationResponseDTO> createStockNotification(@PathVariable Long productId, @Valid @RequestBody StockNotificationRequestDTO notificationRequestDTO) {
        StockNotificationResponseDTO newNotification = stockNotificationService.createStockNotification(productId, notificationRequestDTO);
        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockNotificationResponseDTO> getStockNotificationById(@PathVariable Long id) {
        StockNotificationResponseDTO notification = stockNotificationService.getStockNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<StockNotificationResponseDTO>> getStockNotificationsForProduct(@PathVariable Long productId) {
        List<StockNotificationResponseDTO> notifications = stockNotificationService.getStockNotificationsForProduct(productId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/unnotified")
    public ResponseEntity<List<StockNotificationResponseDTO>> getUnnotifiedStockNotifications() {
        List<StockNotificationResponseDTO> notifications = stockNotificationService.getUnnotifiedStockNotifications();
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}/mark-notified")
    public ResponseEntity<StockNotificationResponseDTO> markNotificationAsNotified(@PathVariable Long id) {
        StockNotificationResponseDTO updatedNotification = stockNotificationService.markNotificationAsNotified(id);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockNotification(@PathVariable Long id) {
        stockNotificationService.deleteStockNotification(id);
        return ResponseEntity.noContent().build();
    }
}
