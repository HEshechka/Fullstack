package net.petproject.backend.controller.util;

import jakarta.validation.Valid;
import net.petproject.backend.dto.util.report.ReportRequestDTO;
import net.petproject.backend.dto.util.report.ReportResponseDTO;
import net.petproject.backend.service.util.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponseDTO> createReport(@Valid @RequestBody ReportRequestDTO reportRequestDTO) {
        ReportResponseDTO newReport = reportService.createReport(reportRequestDTO);
        return new ResponseEntity<>(newReport, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> getReportById(@PathVariable Long id) {
        ReportResponseDTO report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDTO>> getAllReports(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        if (startDate != null && endDate != null) {
            return ResponseEntity.ok(reportService.getReportsByDateRange(startDate, endDate));
        }
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
