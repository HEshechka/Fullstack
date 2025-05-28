package net.petproject.backend.service.product;

import net.petproject.backend.dto.product.product_supplier.ProductSupplierRequestDTO;
import net.petproject.backend.dto.product.product_supplier.ProductSupplierResponseDTO;
import net.petproject.backend.mapper.product.ProductSupplierMapper;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.model.product.ProductSupplier;
import net.petproject.backend.model.supplier.Supplier;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.product.ProductSupplierRepository;
import net.petproject.backend.repository.supplier.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSupplierService {

    private final ProductSupplierRepository productSupplierRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductSupplierMapper productSupplierMapper;

    public ProductSupplierService(ProductSupplierRepository productSupplierRepository, ProductRepository productRepository, SupplierRepository supplierRepository, ProductSupplierMapper productSupplierMapper) {
        this.productSupplierRepository = productSupplierRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.productSupplierMapper = productSupplierMapper;
    }

    @Transactional
    public ProductSupplierResponseDTO addProductToSupplier(ProductSupplierRequestDTO productSupplierRequestDTO) {
        Product product = productRepository.findById(productSupplierRequestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productSupplierRequestDTO.getProductId()));
        Supplier supplier = supplierRepository.findById(productSupplierRequestDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + productSupplierRequestDTO.getSupplierId()));

        if (productSupplierRepository.findByProductAndSupplier(product, supplier).isPresent()) {
            throw new IllegalArgumentException("Product " + product.getProductName() + " is already supplied by " + supplier.getSupplierName());
        }

        ProductSupplier productSupplier = productSupplierMapper.toEntity(productSupplierRequestDTO);
        productSupplier.setProduct(product);
        productSupplier.setSupplier(supplier);
        productSupplier.setAddedDate(LocalDateTime.now());

        ProductSupplier savedProductSupplier = productSupplierRepository.save(productSupplier);
        return productSupplierMapper.toDto(savedProductSupplier);
    }

    @Transactional(readOnly = true)
    public ProductSupplierResponseDTO getProductSupplierById(Long id) {
        ProductSupplier productSupplier = productSupplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Supplier entry not found with ID: " + id));
        return productSupplierMapper.toDto(productSupplier);
    }

    @Transactional(readOnly = true)
    public List<ProductSupplierResponseDTO> getSuppliersForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return productSupplierRepository.findByProduct(product).stream()
                .map(productSupplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductSupplierResponseDTO> getProductsFromSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));
        return productSupplierRepository.findBySupplier(supplier).stream()
                .map(productSupplierMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductSupplierResponseDTO updateProductSupplier(Long id, ProductSupplierRequestDTO productSupplierRequestDTO) {
        ProductSupplier existingProductSupplier = productSupplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Supplier entry not found with ID: " + id));

        if (productSupplierRequestDTO.getSupplyPrice() != null) {
            existingProductSupplier.setSupplyPrice(productSupplierRequestDTO.getSupplyPrice());
        }
        if (productSupplierRequestDTO.getMinOrderQuantity() != null) {
            existingProductSupplier.setMinOrderQuantity(productSupplierRequestDTO.getMinOrderQuantity());
        }

        ProductSupplier updatedProductSupplier = productSupplierRepository.save(existingProductSupplier);
        return productSupplierMapper.toDto(updatedProductSupplier);
    }

    @Transactional
    public void deleteProductSupplier(Long id) {
        productSupplierRepository.deleteById(id);
    }
}
