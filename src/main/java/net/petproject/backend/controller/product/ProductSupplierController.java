package net.petproject.backend.controller.product;

import jakarta.validation.Valid;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierRequestDTO;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierResponseDTO;
import net.petproject.backend.service.product.ProductSupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-suppliers") // Отдельный ресурс для управления связями
public class ProductSupplierController {

    private final ProductSupplierService productSupplierService;

    public ProductSupplierController(ProductSupplierService productSupplierService) {
        this.productSupplierService = productSupplierService;
    }

    @PostMapping
    public ResponseEntity<ProductSupplierResponseDTO> addProductToSupplier(@Valid @RequestBody ProductSupplierRequestDTO productSupplierRequestDTO) {
        ProductSupplierResponseDTO newProductSupplier = productSupplierService.addProductToSupplier(productSupplierRequestDTO);
        return new ResponseEntity<>(newProductSupplier, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSupplierResponseDTO> getProductSupplierById(@PathVariable Long id) {
        ProductSupplierResponseDTO productSupplier = productSupplierService.getProductSupplierById(id);
        return ResponseEntity.ok(productSupplier);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductSupplierResponseDTO>> getSuppliersForProduct(@PathVariable Long productId) {
        List<ProductSupplierResponseDTO> productSuppliers = productSupplierService.getSuppliersForProduct(productId);
        return ResponseEntity.ok(productSuppliers);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductSupplierResponseDTO>> getProductsFromSupplier(@PathVariable Long supplierId) {
        List<ProductSupplierResponseDTO> productSuppliers = productSupplierService.getProductsFromSupplier(supplierId);
        return ResponseEntity.ok(productSuppliers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSupplierResponseDTO> updateProductSupplier(@PathVariable Long id, @Valid @RequestBody ProductSupplierRequestDTO productSupplierRequestDTO) {
        ProductSupplierResponseDTO updatedProductSupplier = productSupplierService.updateProductSupplier(id, productSupplierRequestDTO);
        return ResponseEntity.ok(updatedProductSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductSupplier(@PathVariable Long id) {
        productSupplierService.deleteProductSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
