package net.petproject.backend.mapper.util;

import net.petproject.backend.dto.util.stock_notification.StockNotificationRequestDTO;
import net.petproject.backend.dto.util.stock_notification.StockNotificationResponseDTO;
import net.petproject.backend.model.product.StockNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StockNotificationMapper {
    @Mapping(target = "productId", source = "stockNotification.product.id")
    @Mapping(target = "productName", source = "stockNotification.product.productName")
    StockNotificationResponseDTO toDto(StockNotification stockNotification);

    @Mapping(target = "product", ignore = true) // Product будет установлен в сервисе
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "notificationDate", ignore = true) // Устанавливается на сервере
    @Mapping(target = "isNotified", ignore = true) // Устанавливается на сервере
    StockNotification toEntity(StockNotificationRequestDTO stockNotificationRequestDTO);
}
