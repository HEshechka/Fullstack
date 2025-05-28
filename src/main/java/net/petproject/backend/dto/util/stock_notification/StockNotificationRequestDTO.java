package net.petproject.backend.dto.util.stock_notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockNotificationRequestDTO {
    // productId будет взят из URL
    private Integer threshold;
    // notificationDate и isNotified будут установлены на сервере
}
