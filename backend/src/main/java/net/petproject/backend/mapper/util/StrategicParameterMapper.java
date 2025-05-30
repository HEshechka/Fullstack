package net.petproject.backend.mapper.util;

import net.petproject.backend.dto.util.StrategicParameterDTO;
import net.petproject.backend.model.product.StrategicParameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StrategicParameterMapper {
    StrategicParameterDTO toDto(StrategicParameter strategicParameter);

    @Mapping(target = "id", ignore = true) // ID генерируется БД
    StrategicParameter toEntity(StrategicParameterDTO strategicParameterDTO);
}
