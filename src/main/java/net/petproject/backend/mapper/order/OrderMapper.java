package net.petproject.backend.mapper.order;

import net.petproject.backend.dto.order.OrderRequestDTO;
import net.petproject.backend.dto.order.OrderResponseDTO;
import net.petproject.backend.mapper.user.AddressMapper;
import net.petproject.backend.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {
        OrderItemMapper.class,
        AddressMapper.class,
        OrderStatusMapper.class,
        DeliveryMapper.class
})
public interface OrderMapper {
    @Mapping(target = "userId", source = "order.user.id")
    @Mapping(target = "customerFullName", expression = "java(order.getUser().getFirstName() + \" \" + order.getUser().getLastName())")
    @Mapping(target = "billingAddress", source = "order.billingAddress")
    @Mapping(target = "shippingAddress", source = "order.shippingAddress")
    @Mapping(target = "status", source = "order.status")
    @Mapping(target = "items", source = "order.orderItems")
    @Mapping(target = "delivery", source = "order.delivery")
    OrderResponseDTO toDto(Order order);

    // При маппинге OrderRequestDTO на Order Entity, внешние ключи будут установлены в сервисе
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "billingAddress", ignore = true)
    @Mapping(target = "shippingAddress", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderItems", ignore = true) // Items будут добавлены в сервисе
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true) // Устанавливается через @CreationTimestamp
    @Mapping(target = "totalAmount", ignore = true) // Вычисляется в сервисе
    Order toEntity(OrderRequestDTO orderRequestDTO);
}
