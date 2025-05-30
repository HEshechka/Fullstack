package net.petproject.backend.service.user;

import net.petproject.backend.dto.user.address.AddressRequestDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;
import net.petproject.backend.mapper.user.AddressMapper;
import net.petproject.backend.model.user.Address;
import net.petproject.backend.model.user.User;
import net.petproject.backend.repository.user.AddressRepository;
import net.petproject.backend.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public AddressResponseDTO createAddress(Long userId, AddressRequestDTO addressRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Address address = addressMapper.toEntity(addressRequestDTO);
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDto(savedAddress);
    }

    @Transactional(readOnly = true)
    public AddressResponseDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
        return addressMapper.toDto(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponseDTO> getAddressesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return addressRepository.findByUser(user).stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressResponseDTO updateAddress(Long id, Long userId, AddressRequestDTO addressRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Address existingAddress = addressRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Address not found or does not belong to user with ID: " + id));

        if (addressRequestDTO.getStreet() != null) {
            existingAddress.setStreet(addressRequestDTO.getStreet());
        }
        if (addressRequestDTO.getCity() != null) {
            existingAddress.setCity(addressRequestDTO.getCity());
        }
        if (addressRequestDTO.getState() != null) {
            existingAddress.setState(addressRequestDTO.getState());
        }
        if (addressRequestDTO.getZipCode() != null) {
            existingAddress.setZipCode(addressRequestDTO.getZipCode());
        }
        if (addressRequestDTO.getCountry() != null) {
            existingAddress.setCountry(addressRequestDTO.getCountry());
        }
        if (addressRequestDTO.getAddressType() != null) {
            existingAddress.setAddressType(addressRequestDTO.getAddressType());
        }
        if (addressRequestDTO.getIsDefault() != null) {
            existingAddress.setIsDefault(addressRequestDTO.getIsDefault());
        }

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toDto(updatedAddress);
    }

    @Transactional
    public void deleteAddress(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Address address = addressRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Address not found or does not belong to user with ID: " + id));
        addressRepository.delete(address);
    }
}
