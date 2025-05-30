package net.petproject.backend.mapper.order;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.order.OrderStatusDTO;
import net.petproject.backend.model.order.OrderStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class OrderStatusMapperImpl implements OrderStatusMapper {

    @Override
    public OrderStatusDTO toDto(OrderStatus orderStatus) {
        if ( orderStatus == null ) {
            return null;
        }

        OrderStatusDTO.OrderStatusDTOBuilder orderStatusDTO = OrderStatusDTO.builder();

        orderStatusDTO.id( orderStatus.getId() );
        orderStatusDTO.statusName( orderStatus.getStatusName() );

        return orderStatusDTO.build();
    }
}
