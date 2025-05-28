package net.petproject.backend.controller.product;

import jakarta.validation.Valid;
import net.petproject.backend.dto.price.PriceChangeRequestDTO;
import net.petproject.backend.dto.price.PriceChangeResponseDTO;
import net.petproject.backend.service.product.PriceChangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/price-changes") // Изменение цены для конкретного продукта
public class PriceChangeController {

    private final PriceChangeService priceChangeService;

    public PriceChangeController(PriceChangeService priceChangeService) {
        this.priceChangeService = priceChangeService;
    }

    @PostMapping
    public ResponseEntity<PriceChangeResponseDTO> recordPriceChange(@PathVariable Long productId, @Valid @RequestBody PriceChangeRequestDTO priceChangeRequestDTO) {
        PriceChangeResponseDTO newPriceChange = priceChangeService.recordPriceChange(productId, priceChangeRequestDTO);
        return new ResponseEntity<>(newPriceChange, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceChangeResponseDTO> getPriceChangeById(@PathVariable Long id) {
        PriceChangeResponseDTO priceChange = priceChangeService.getPriceChangeById(id);
        return ResponseEntity.ok(priceChange);
    }

    @GetMapping
    public ResponseEntity<List<PriceChangeResponseDTO>> getPriceChangesForProduct(@PathVariable Long productId) {
        List<PriceChangeResponseDTO> priceChanges = priceChangeService.getPriceChangesForProduct(productId);
        return ResponseEntity.ok(priceChanges);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceChange(@PathVariable Long id) {
        priceChangeService.deletePriceChange(id);
        return ResponseEntity.noContent().build();
    }
}
