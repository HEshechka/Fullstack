package net.petproject.backend.dto.order;

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
public class CreateOrderFromCartDTO {
    private Long billingAddressId;
    private Long shippingAddressId;
    private String paymentMethod;
    // statusId будет по умолчанию (например, "PENDING")
}
