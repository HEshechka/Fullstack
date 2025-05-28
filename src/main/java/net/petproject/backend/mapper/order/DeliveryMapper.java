package net.petproject.backend.mapper.order;

import net.petproject.backend.dto.order.delivery.DeliveryRequestDTO;
import net.petproject.backend.dto.order.delivery.DeliveryResponseDTO;
import net.petproject.backend.model.order.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @Mapping(target = "orderId", source = "delivery.order.id")
    DeliveryResponseDTO toDto(Delivery delivery);

    @Mapping(target = "order", ignore = true) // Order будет установлен в сервисе
    @Mapping(target = "id", ignore = true)
    Delivery toEntity(DeliveryRequestDTO deliveryRequestDTO);
}
