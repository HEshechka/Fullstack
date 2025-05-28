package net.petproject.backend.service.supplier;

import net.petproject.backend.dto.supplier.SupplierRequestDTO;
import net.petproject.backend.dto.supplier.SupplierResponseDTO;
import net.petproject.backend.mapper.supplier.SupplierMapper;
import net.petproject.backend.model.supplier.Supplier;
import net.petproject.backend.repository.supplier.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Transactional
    public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
        if (supplierRepository.findByEmail(supplierRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Supplier with this email already exists: " + supplierRequestDTO.getEmail());
        }
        Supplier supplier = supplierMapper.toEntity(supplierRequestDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(savedSupplier);
    }

    @Transactional(readOnly = true)
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
        return supplierMapper.toDto(supplier);
    }

    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO supplierRequestDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));

        if (supplierRequestDTO.getSupplierName() != null) {
            existingSupplier.setSupplierName(supplierRequestDTO.getSupplierName());
        }
        if (supplierRequestDTO.getContactPerson() != null) {
            existingSupplier.setContactPerson(supplierRequestDTO.getContactPerson());
        }
        if (supplierRequestDTO.getPhoneNumber() != null) {
            existingSupplier.setPhoneNumber(supplierRequestDTO.getPhoneNumber());
        }
        if (supplierRequestDTO.getEmail() != null && !existingSupplier.getEmail().equals(supplierRequestDTO.getEmail())) {
            if (supplierRepository.findByEmail(supplierRequestDTO.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Supplier with this email already exists: " + supplierRequestDTO.getEmail());
            }
            existingSupplier.setEmail(supplierRequestDTO.getEmail());
        }

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return supplierMapper.toDto(updatedSupplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
