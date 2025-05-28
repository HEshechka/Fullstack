package net.petproject.backend.service.util;

import net.petproject.backend.dto.util.stock_notification.StockNotificationRequestDTO;
import net.petproject.backend.dto.util.stock_notification.StockNotificationResponseDTO;
import net.petproject.backend.mapper.util.StockNotificationMapper;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.StockNotification;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.util.StockNotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockNotificationService {

    private final StockNotificationRepository stockNotificationRepository;
    private final ProductRepository productRepository;
    private final StockNotificationMapper stockNotificationMapper;

    public StockNotificationService(StockNotificationRepository stockNotificationRepository, ProductRepository productRepository, StockNotificationMapper stockNotificationMapper) {
        this.stockNotificationRepository = stockNotificationRepository;
        this.productRepository = productRepository;
        this.stockNotificationMapper = stockNotificationMapper;
    }

    @Transactional
    public StockNotificationResponseDTO createStockNotification(Long productId, StockNotificationRequestDTO notificationRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        StockNotification notification = stockNotificationMapper.toEntity(notificationRequestDTO);
        notification.setProduct(product);
        notification.setNotificationDate(LocalDateTime.now());
        notification.setIsNotified(false); // По умолчанию, уведомление еще не отправлено

        StockNotification savedNotification = stockNotificationRepository.save(notification);
        return stockNotificationMapper.toDto(savedNotification);
    }

    @Transactional(readOnly = true)
    public StockNotificationResponseDTO getStockNotificationById(Long id) {
        StockNotification notification = stockNotificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Notification not found with ID: " + id));
        return stockNotificationMapper.toDto(notification);
    }

    @Transactional(readOnly = true)
    public List<StockNotificationResponseDTO> getStockNotificationsForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return stockNotificationRepository.findByProduct(product).stream()
                .map(stockNotificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StockNotificationResponseDTO> getUnnotifiedStockNotifications() {
        return stockNotificationRepository.findByIsNotifiedFalse().stream()
                .map(stockNotificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public StockNotificationResponseDTO markNotificationAsNotified(Long id) {
        StockNotification notification = stockNotificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock Notification not found with ID: " + id));
        notification.setIsNotified(true);
        StockNotification updatedNotification = stockNotificationRepository.save(notification);
        return stockNotificationMapper.toDto(updatedNotification);
    }

    @Transactional
    public void deleteStockNotification(Long id) {
        stockNotificationRepository.deleteById(id);
    }
}
