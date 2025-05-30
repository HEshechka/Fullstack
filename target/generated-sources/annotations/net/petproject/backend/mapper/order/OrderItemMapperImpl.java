package net.petproject.backend.mapper.order;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.order.order_item.OrderItemRequestDTO;
import net.petproject.backend.dto.order.order_item.OrderItemResponseDTO;
import net.petproject.backend.model.order.Order;
import net.petproject.backend.model.order.OrderItem;
import net.petproject.backend.model.product.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemResponseDTO toDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponseDTO.OrderItemResponseDTOBuilder orderItemResponseDTO = OrderItemResponseDTO.builder();

        orderItemResponseDTO.orderId( orderItemOrderId( orderItem ) );
        orderItemResponseDTO.productId( orderItemProductId( orderItem ) );
        orderItemResponseDTO.productName( orderItemProductProductName( orderItem ) );
        orderItemResponseDTO.id( orderItem.getId() );
        orderItemResponseDTO.quantity( orderItem.getQuantity() );
        orderItemResponseDTO.unitPrice( orderItem.getUnitPrice() );

        return orderItemResponseDTO.build();
    }

    @Override
    public OrderItem toEntity(OrderItemRequestDTO orderItemRequestDTO) {
        if ( orderItemRequestDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.quantity( orderItemRequestDTO.getQuantity() );

        return orderItem.build();
    }

    private Long orderItemOrderId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Order order = orderItem.getOrder();
        if ( order == null ) {
            return null;
        }
        Long id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderItemProductProductName(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }
}
