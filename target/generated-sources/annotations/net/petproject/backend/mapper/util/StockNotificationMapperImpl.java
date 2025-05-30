package net.petproject.backend.mapper.util;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.util.stock_notification.StockNotificationRequestDTO;
import net.petproject.backend.dto.util.stock_notification.StockNotificationResponseDTO;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.StockNotification;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class StockNotificationMapperImpl implements StockNotificationMapper {

    @Override
    public StockNotificationResponseDTO toDto(StockNotification stockNotification) {
        if ( stockNotification == null ) {
            return null;
        }

        StockNotificationResponseDTO.StockNotificationResponseDTOBuilder stockNotificationResponseDTO = StockNotificationResponseDTO.builder();

        stockNotificationResponseDTO.productId( stockNotificationProductId( stockNotification ) );
        stockNotificationResponseDTO.productName( stockNotificationProductProductName( stockNotification ) );
        stockNotificationResponseDTO.id( stockNotification.getId() );
        stockNotificationResponseDTO.threshold( stockNotification.getThreshold() );
        stockNotificationResponseDTO.notificationDate( stockNotification.getNotificationDate() );
        stockNotificationResponseDTO.isNotified( stockNotification.getIsNotified() );

        return stockNotificationResponseDTO.build();
    }

    @Override
    public StockNotification toEntity(StockNotificationRequestDTO stockNotificationRequestDTO) {
        if ( stockNotificationRequestDTO == null ) {
            return null;
        }

        StockNotification.StockNotificationBuilder stockNotification = StockNotification.builder();

        stockNotification.threshold( stockNotificationRequestDTO.getThreshold() );

        return stockNotification.build();
    }

    private Long stockNotificationProductId(StockNotification stockNotification) {
        if ( stockNotification == null ) {
            return null;
        }
        Product product = stockNotification.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String stockNotificationProductProductName(StockNotification stockNotification) {
        if ( stockNotification == null ) {
            return null;
        }
        Product product = stockNotification.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }
}
