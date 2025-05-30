package net.petproject.backend.mapper.order;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.order.delivery.DeliveryRequestDTO;
import net.petproject.backend.dto.order.delivery.DeliveryResponseDTO;
import net.petproject.backend.model.order.Delivery;
import net.petproject.backend.model.order.Order;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class DeliveryMapperImpl implements DeliveryMapper {

    @Override
    public DeliveryResponseDTO toDto(Delivery delivery) {
        if ( delivery == null ) {
            return null;
        }

        DeliveryResponseDTO.DeliveryResponseDTOBuilder deliveryResponseDTO = DeliveryResponseDTO.builder();

        deliveryResponseDTO.orderId( deliveryOrderId( delivery ) );
        deliveryResponseDTO.id( delivery.getId() );
        deliveryResponseDTO.deliveryDate( delivery.getDeliveryDate() );
        deliveryResponseDTO.status( delivery.getStatus() );
        deliveryResponseDTO.trackingNumber( delivery.getTrackingNumber() );
        deliveryResponseDTO.carrier( delivery.getCarrier() );

        return deliveryResponseDTO.build();
    }

    @Override
    public Delivery toEntity(DeliveryRequestDTO deliveryRequestDTO) {
        if ( deliveryRequestDTO == null ) {
            return null;
        }

        Delivery.DeliveryBuilder delivery = Delivery.builder();

        delivery.deliveryDate( deliveryRequestDTO.getDeliveryDate() );
        delivery.status( deliveryRequestDTO.getStatus() );
        delivery.trackingNumber( deliveryRequestDTO.getTrackingNumber() );
        delivery.carrier( deliveryRequestDTO.getCarrier() );

        return delivery.build();
    }

    private Long deliveryOrderId(Delivery delivery) {
        if ( delivery == null ) {
            return null;
        }
        Order order = delivery.getOrder();
        if ( order == null ) {
            return null;
        }
        Long id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
