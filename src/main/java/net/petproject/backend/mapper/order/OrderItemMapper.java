package net.petproject.backend.mapper.order;

import net.petproject.backend.dto.order.order_item.OrderItemRequestDTO;
import net.petproject.backend.dto.order.order_item.OrderItemResponseDTO;
import net.petproject.backend.mapper.product.ProductMapper;
import net.petproject.backend.model.order.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // Используем ProductMapper
public interface OrderItemMapper {
    @Mapping(target = "orderId", source = "orderItem.order.id")
    @Mapping(target = "productId", source = "orderItem.product.id")
    @Mapping(target = "productName", source = "orderItem.product.productName")
    OrderItemResponseDTO toDto(OrderItem orderItem);

    @Mapping(target = "order", ignore = true) // Order и Product будут установлены в сервисе
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unitPrice", ignore = true) // unitPrice берется из Product в сервисе
    OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO);
}
