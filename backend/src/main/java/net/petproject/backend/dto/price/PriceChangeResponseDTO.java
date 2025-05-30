package net.petproject.backend.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceChangeResponseDTO {
    private Long id;
    private Long productId; // Можно включить только ID, если продукт загружается отдельно
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private LocalDateTime changeDate;
    private String reason;
}
