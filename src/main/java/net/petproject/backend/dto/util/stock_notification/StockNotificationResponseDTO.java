package net.petproject.backend.dto.util.stock_notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockNotificationResponseDTO {
    private Long id;
    private Long productId;
    private String productName; // Для удобства
    private Integer threshold;
    private LocalDateTime notificationDate;
    private Boolean isNotified;
}
