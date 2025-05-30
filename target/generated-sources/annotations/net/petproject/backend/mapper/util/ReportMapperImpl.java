package net.petproject.backend.mapper.util;

import javax.annotation.processing.Generated;
import net.petproject.backend.dto.util.report.ReportRequestDTO;
import net.petproject.backend.dto.util.report.ReportResponseDTO;
import net.petproject.backend.model.util.Report;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T11:01:23+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public ReportResponseDTO toDto(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportResponseDTO.ReportResponseDTOBuilder reportResponseDTO = ReportResponseDTO.builder();

        reportResponseDTO.id( report.getId() );
        reportResponseDTO.reportName( report.getReportName() );
        reportResponseDTO.startDate( report.getStartDate() );
        reportResponseDTO.endDate( report.getEndDate() );
        reportResponseDTO.createdAt( report.getCreatedAt() );
        reportResponseDTO.reportData( report.getReportData() );

        return reportResponseDTO.build();
    }

    @Override
    public Report toEntity(ReportRequestDTO reportRequestDTO) {
        if ( reportRequestDTO == null ) {
            return null;
        }

        Report.ReportBuilder report = Report.builder();

        report.reportName( reportRequestDTO.getReportName() );
        report.startDate( reportRequestDTO.getStartDate() );
        report.endDate( reportRequestDTO.getEndDate() );

        return report.build();
    }
}
