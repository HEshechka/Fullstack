package net.petproject.backend.controller.user;

import jakarta.validation.Valid;
import net.petproject.backend.dto.user.address.AddressRequestDTO;
import net.petproject.backend.dto.user.address.AddressResponseDTO;
import net.petproject.backend.service.user.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/addresses") // Адреса принадлежат пользователям
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@PathVariable Long userId, @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO newAddress = addressService.createAddress(userId, addressRequestDTO);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long id) {
        AddressResponseDTO address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAddressesByUserId(@PathVariable Long userId) {
        List<AddressResponseDTO> addresses = addressService.getAddressesByUserId(userId);
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO updatedAddress = addressService.updateAddress(id, userId, addressRequestDTO);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long userId, @PathVariable Long id) {
        addressService.deleteAddress(id, userId);
        return ResponseEntity.noContent().build();
    }
}
