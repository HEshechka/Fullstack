package net.petproject.backend.mapper.util;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.util.StrategicParameterDTO;
import net.petproject.backend.model.product.StrategicParameter;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class StrategicParameterMapperImpl implements StrategicParameterMapper {

    @Override
    public StrategicParameterDTO toDto(StrategicParameter strategicParameter) {
        if ( strategicParameter == null ) {
            return null;
        }

        StrategicParameterDTO.StrategicParameterDTOBuilder strategicParameterDTO = StrategicParameterDTO.builder();

        strategicParameterDTO.id( strategicParameter.getId() );
        strategicParameterDTO.parameterName( strategicParameter.getParameterName() );
        strategicParameterDTO.value( strategicParameter.getValue() );
        strategicParameterDTO.startDate( strategicParameter.getStartDate() );
        strategicParameterDTO.endDate( strategicParameter.getEndDate() );
        strategicParameterDTO.dataType( strategicParameter.getDataType() );
        strategicParameterDTO.parameterType( strategicParameter.getParameterType() );

        return strategicParameterDTO.build();
    }

    @Override
    public StrategicParameter toEntity(StrategicParameterDTO strategicParameterDTO) {
        if ( strategicParameterDTO == null ) {
            return null;
        }

        StrategicParameter.StrategicParameterBuilder strategicParameter = StrategicParameter.builder();

        strategicParameter.parameterName( strategicParameterDTO.getParameterName() );
        strategicParameter.value( strategicParameterDTO.getValue() );
        strategicParameter.startDate( strategicParameterDTO.getStartDate() );
        strategicParameter.endDate( strategicParameterDTO.getEndDate() );
        strategicParameter.dataType( strategicParameterDTO.getDataType() );
        strategicParameter.parameterType( strategicParameterDTO.getParameterType() );

        return strategicParameter.build();
    }
}
