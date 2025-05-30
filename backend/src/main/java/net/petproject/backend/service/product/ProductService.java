package net.petproject.backend.service.product;

import net.petproject.backend.dto.product.ProductRequestDTO;
import net.petproject.backend.dto.product.ProductResponseDTO;
import net.petproject.backend.mapper.product.ProductMapper;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return productMapper.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        // Маппинг обновляемых полей
        if (productRequestDTO.getProductName() != null) {
            existingProduct.setProductName(productRequestDTO.getProductName());
        }
        if (productRequestDTO.getDescription() != null) {
            existingProduct.setDescription(productRequestDTO.getDescription());
        }
        if (productRequestDTO.getPrice() != null) {
            existingProduct.setPrice(productRequestDTO.getPrice());
        }
        if (productRequestDTO.getStockQuantity() != null) {
            existingProduct.setStockQuantity(productRequestDTO.getStockQuantity());
        }
        // updatedAt будет обновлен автоматически через @UpdateTimestamp

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
