package net.petproject.backend.mapper.order;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import net.petproject.backend.dto.order.OrderRequestDTO;
import net.petproject.backend.dto.order.OrderResponseDTO;
import net.petproject.backend.dto.order.order_item.OrderItemResponseDTO;
import net.petproject.backend.mapper.user.AddressMapper;
import net.petproject.backend.model.order.Order;
import net.petproject.backend.model.order.OrderItem;
import net.petproject.backend.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public OrderResponseDTO toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDTO.OrderResponseDTOBuilder orderResponseDTO = OrderResponseDTO.builder();

        orderResponseDTO.userId( orderUserId( order ) );
        orderResponseDTO.billingAddress( addressMapper.toDto( order.getBillingAddress() ) );
        orderResponseDTO.shippingAddress( addressMapper.toDto( order.getShippingAddress() ) );
        orderResponseDTO.status( orderStatusMapper.toDto( order.getStatus() ) );
        orderResponseDTO.items( orderItemSetToOrderItemResponseDTOSet( order.getOrderItems() ) );
        orderResponseDTO.delivery( deliveryMapper.toDto( order.getDelivery() ) );
        orderResponseDTO.id( order.getId() );
        orderResponseDTO.orderDate( order.getOrderDate() );
        orderResponseDTO.totalAmount( order.getTotalAmount() );
        orderResponseDTO.paymentMethod( order.getPaymentMethod() );
        orderResponseDTO.paymentStatus( order.getPaymentStatus() );

        orderResponseDTO.customerFullName( order.getUser().getFirstName() + " " + order.getUser().getLastName() );

        return orderResponseDTO.build();
    }

    @Override
    public Order toEntity(OrderRequestDTO orderRequestDTO) {
        if ( orderRequestDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.paymentMethod( orderRequestDTO.getPaymentMethod() );

        return order.build();
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<OrderItemResponseDTO> orderItemSetToOrderItemResponseDTOSet(Set<OrderItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderItemResponseDTO> set1 = new LinkedHashSet<OrderItemResponseDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderItem orderItem : set ) {
            set1.add( orderItemMapper.toDto( orderItem ) );
        }

        return set1;
    }
}
