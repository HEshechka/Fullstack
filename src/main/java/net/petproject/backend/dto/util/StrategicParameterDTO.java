package net.petproject.backend.dto.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrategicParameterDTO { // Обычно используется один DTO для чтения и записи
    private Long id;
    private String parameterName;
    private String value;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String dataType;
    private String parameterType;
}
