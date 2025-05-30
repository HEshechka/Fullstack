package net.petproject.backend.mapper.product;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.price.PriceChangeRequestDTO;
import net.petproject.backend.dto.price.PriceChangeResponseDTO;
import net.petproject.backend.model.product.PriceChange;
import net.petproject.backend.model.product.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PriceChangeMapperImpl implements PriceChangeMapper {

    @Override
    public PriceChangeResponseDTO toDto(PriceChange priceChange) {
        if ( priceChange == null ) {
            return null;
        }

        PriceChangeResponseDTO.PriceChangeResponseDTOBuilder priceChangeResponseDTO = PriceChangeResponseDTO.builder();

        priceChangeResponseDTO.productId( priceChangeProductId( priceChange ) );
        priceChangeResponseDTO.id( priceChange.getId() );
        priceChangeResponseDTO.oldPrice( priceChange.getOldPrice() );
        priceChangeResponseDTO.newPrice( priceChange.getNewPrice() );
        priceChangeResponseDTO.changeDate( priceChange.getChangeDate() );
        priceChangeResponseDTO.reason( priceChange.getReason() );

        return priceChangeResponseDTO.build();
    }

    @Override
    public PriceChange toEntity(PriceChangeRequestDTO priceChangeRequestDTO) {
        if ( priceChangeRequestDTO == null ) {
            return null;
        }

        PriceChange.PriceChangeBuilder priceChange = PriceChange.builder();

        priceChange.newPrice( priceChangeRequestDTO.getNewPrice() );
        priceChange.reason( priceChangeRequestDTO.getReason() );

        return priceChange.build();
    }

    private Long priceChangeProductId(PriceChange priceChange) {
        if ( priceChange == null ) {
            return null;
        }
        Product product = priceChange.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
