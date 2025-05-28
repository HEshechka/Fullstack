package net.petproject.backend.service.util;

import net.petproject.backend.dto.util.StrategicParameterDTO;
import net.petproject.backend.mapper.util.StrategicParameterMapper;
import net.petproject.backend.model.product.StrategicParameter;
import net.petproject.backend.repository.util.StrategicParameterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StrategicParameterService {

    private final StrategicParameterRepository strategicParameterRepository;
    private final StrategicParameterMapper strategicParameterMapper;

    public StrategicParameterService(StrategicParameterRepository strategicParameterRepository, StrategicParameterMapper strategicParameterMapper) {
        this.strategicParameterRepository = strategicParameterRepository;
        this.strategicParameterMapper = strategicParameterMapper;
    }

    @Transactional
    public StrategicParameterDTO createParameter(StrategicParameterDTO parameterDTO) {
        if (strategicParameterRepository.findByParameterName(parameterDTO.getParameterName()).isPresent()) {
            throw new IllegalArgumentException("Strategic parameter with this name already exists: " + parameterDTO.getParameterName());
        }
        StrategicParameter parameter = strategicParameterMapper.toEntity(parameterDTO);
        StrategicParameter savedParameter = strategicParameterRepository.save(parameter);
        return strategicParameterMapper.toDto(savedParameter);
    }

    @Transactional(readOnly = true)
    public StrategicParameterDTO getParameterById(Long id) {
        StrategicParameter parameter = strategicParameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Strategic Parameter not found with ID: " + id));
        return strategicParameterMapper.toDto(parameter);
    }

    @Transactional(readOnly = true)
    public StrategicParameterDTO getParameterByName(String name) {
        StrategicParameter parameter = strategicParameterRepository.findByParameterName(name)
                .orElseThrow(() -> new RuntimeException("Strategic Parameter not found with name: " + name));
        return strategicParameterMapper.toDto(parameter);
    }

    @Transactional(readOnly = true)
    public List<StrategicParameterDTO> getAllParameters() {
        return strategicParameterRepository.findAll().stream()
                .map(strategicParameterMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public StrategicParameterDTO updateParameter(Long id, StrategicParameterDTO parameterDTO) {
        StrategicParameter existingParameter = strategicParameterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Strategic Parameter not found with ID: " + id));

        // Обновляем поля, если они предоставлены
        if (parameterDTO.getParameterName() != null && !existingParameter.getParameterName().equals(parameterDTO.getParameterName())) {
             if (strategicParameterRepository.findByParameterName(parameterDTO.getParameterName()).isPresent()) {
                throw new IllegalArgumentException("Strategic parameter with this name already exists: " + parameterDTO.getParameterName());
            }
            existingParameter.setParameterName(parameterDTO.getParameterName());
        }
        if (parameterDTO.getValue() != null) {
            existingParameter.setValue(parameterDTO.getValue());
        }
        if (parameterDTO.getStartDate() != null) {
            existingParameter.setStartDate(parameterDTO.getStartDate());
        }
        if (parameterDTO.getEndDate() != null) {
            existingParameter.setEndDate(parameterDTO.getEndDate());
        }
        if (parameterDTO.getDataType() != null) {
            existingParameter.setDataType(parameterDTO.getDataType());
        }
        if (parameterDTO.getParameterType() != null) {
            existingParameter.setParameterType(parameterDTO.getParameterType());
        }

        StrategicParameter updatedParameter = strategicParameterRepository.save(existingParameter);
        return strategicParameterMapper.toDto(updatedParameter);
    }

    @Transactional
    public void deleteParameter(Long id) {
        strategicParameterRepository.deleteById(id);
    }
}
