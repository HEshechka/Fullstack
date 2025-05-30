package net.petproject.backend.service.util;

import net.petproject.backend.dto.util.report.ReportRequestDTO;
import net.petproject.backend.dto.util.report.ReportResponseDTO;
import net.petproject.backend.mapper.util.ReportMapper;
import net.petproject.backend.model.util.Report;
import net.petproject.backend.repository.util.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    @Transactional
    public ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO) {
        Report report = reportMapper.toEntity(reportRequestDTO);
        report.setCreatedAt(LocalDateTime.now());
        // В реальном приложении здесь будет логика генерации reportData
        report.setReportData("Generated report data for " + report.getReportName() + " from " + report.getStartDate() + " to " + report.getEndDate());

        Report savedReport = reportRepository.save(report);
        return reportMapper.toDto(savedReport);
    }

    @Transactional(readOnly = true)
    public ReportResponseDTO getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with ID: " + id));
        return reportMapper.toDto(report);
    }

    @Transactional(readOnly = true)
    public List<ReportResponseDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportResponseDTO> getReportsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findByCreatedAtBetween(startDate, endDate).stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
