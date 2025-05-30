package net.petproject.backend.mapper.util;

import net.petproject.backend.model.util.Report;
import net.petproject.backend.dto.util.report.ReportRequestDTO;
import net.petproject.backend.dto.util.report.ReportResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportResponseDTO toDto(Report report);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true) // Устанавливается на сервере
    Report toEntity(ReportRequestDTO reportRequestDTO);
}
