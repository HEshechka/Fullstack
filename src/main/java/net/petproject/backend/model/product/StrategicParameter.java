package net.petproject.backend.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "strategic_parameters")
public class StrategicParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;

    @Column(name = "parameter_name", unique = true, nullable = false)
    private String parameterName;

    @Column(name = "value", columnDefinition = "TEXT", nullable = false) // Хранить значение как текст
    private String value;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "data_type") // Например, "STRING", "INTEGER", "DECIMAL", "BOOLEAN"
    private String dataType;

    @Column(name = "parameter_type") // Например, "CONFIGURATION", "THRESHOLD", "BUSINESS_RULE"
    private String parameterType;
}
