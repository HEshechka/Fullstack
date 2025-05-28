package net.petproject.backend.mapper.order;

import net.petproject.backend.dto.order.OrderStatusDTO;
import net.petproject.backend.model.order.OrderStatus;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatusDTO toDto(OrderStatus orderStatus);

    // Если нужна возможность создания статуса из DTO
    // @Mapping(target = "id", ignore = true)
    // OrderStatus toEntity(OrderStatusDTO orderStatusDTO);
}
