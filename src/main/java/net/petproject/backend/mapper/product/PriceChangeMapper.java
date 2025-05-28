package net.petproject.backend.mapper.product;

import net.petproject.backend.dto.price.PriceChangeRequestDTO;
import net.petproject.backend.dto.price.PriceChangeResponseDTO;
import net.petproject.backend.model.product.PriceChange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PriceChangeMapper {
    @Mapping(target = "productId", source = "priceChange.product.id")
    PriceChangeResponseDTO toDto(PriceChange priceChange);

    @Mapping(target = "product", ignore = true) // Product будет установлен в сервисе
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "oldPrice", ignore = true) // Old price будет взят из текущего продукта в сервисе
    @Mapping(target = "changeDate", ignore = true) // Устанавливается в сервисе
    PriceChange toEntity(PriceChangeRequestDTO priceChangeRequestDTO);
}
