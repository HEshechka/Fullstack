package net.petproject.backend.repository.util;

import net.petproject.backend.model.util.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportName(String reportName);
    List<Report> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
