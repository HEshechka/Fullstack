package net.petproject.backend.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceChangeRequestDTO {
    // product_id будет взят из URL или пути запроса
    private BigDecimal newPrice;
    private String reason;
}
