package net.petproject.backend.dto.order.delivery;

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
public class DeliveryRequestDTO {
    // orderId будет взят из URL
    private LocalDateTime deliveryDate;
    private String status;
    private String trackingNumber;
    private String carrier;
}
