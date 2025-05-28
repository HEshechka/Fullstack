package net.petproject.backend.controller.util;

import jakarta.validation.Valid;
import net.petproject.backend.dto.util.StrategicParameterDTO;
import net.petproject.backend.service.util.StrategicParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/strategic-parameters")
public class StrategicParameterController {

    private final StrategicParameterService strategicParameterService;

    public StrategicParameterController(StrategicParameterService strategicParameterService) {
        this.strategicParameterService = strategicParameterService;
    }

    @PostMapping
    public ResponseEntity<StrategicParameterDTO> createParameter(@Valid @RequestBody StrategicParameterDTO parameterDTO) {
        StrategicParameterDTO newParameter = strategicParameterService.createParameter(parameterDTO);
        return new ResponseEntity<>(newParameter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StrategicParameterDTO> getParameterById(@PathVariable Long id) {
        StrategicParameterDTO parameter = strategicParameterService.getParameterById(id);
        return ResponseEntity.ok(parameter);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<StrategicParameterDTO> getParameterByName(@PathVariable String name) {
        StrategicParameterDTO parameter = strategicParameterService.getParameterByName(name);
        return ResponseEntity.ok(parameter);
    }

    @GetMapping
    public ResponseEntity<List<StrategicParameterDTO>> getAllParameters() {
        List<StrategicParameterDTO> parameters = strategicParameterService.getAllParameters();
        return ResponseEntity.ok(parameters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StrategicParameterDTO> updateParameter(@PathVariable Long id, @Valid @RequestBody StrategicParameterDTO parameterDTO) {
        StrategicParameterDTO updatedParameter = strategicParameterService.updateParameter(id, parameterDTO);
        return ResponseEntity.ok(updatedParameter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Long id) {
        strategicParameterService.deleteParameter(id);
        return ResponseEntity.noContent().build();
    }
}
