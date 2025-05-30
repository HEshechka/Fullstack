package net.petproject.backend.service.product;

import net.petproject.backend.dto.price.PriceChangeRequestDTO;
import net.petproject.backend.dto.price.PriceChangeResponseDTO;
import net.petproject.backend.mapper.product.PriceChangeMapper;
import net.petproject.backend.model.product.PriceChange;
import net.petproject.backend.model.product.Product;
import net.petproject.backend.repository.product.ProductRepository;
import net.petproject.backend.repository.util.PriceChangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceChangeService {

    private final PriceChangeRepository priceChangeRepository;
    private final ProductRepository productRepository;
    private final PriceChangeMapper priceChangeMapper;

    public PriceChangeService(PriceChangeRepository priceChangeRepository, ProductRepository productRepository, PriceChangeMapper priceChangeMapper) {
        this.priceChangeRepository = priceChangeRepository;
        this.productRepository = productRepository;
        this.priceChangeMapper = priceChangeMapper;
    }

    @Transactional
    public PriceChangeResponseDTO recordPriceChange(Long productId, PriceChangeRequestDTO priceChangeRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        // Записываем старую цену продукта
        PriceChange priceChange = priceChangeMapper.toEntity(priceChangeRequestDTO);
        priceChange.setProduct(product);
        priceChange.setOldPrice(product.getPrice());
        priceChange.setNewPrice(priceChangeRequestDTO.getNewPrice());
        priceChange.setChangeDate(LocalDateTime.now());

        // Обновляем цену продукта
        product.setPrice(priceChangeRequestDTO.getNewPrice());
        productRepository.save(product);

        PriceChange savedPriceChange = priceChangeRepository.save(priceChange);
        return priceChangeMapper.toDto(savedPriceChange);
    }

    @Transactional(readOnly = true)
    public PriceChangeResponseDTO getPriceChangeById(Long id) {
        PriceChange priceChange = priceChangeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price Change not found with ID: " + id));
        return priceChangeMapper.toDto(priceChange);
    }

    @Transactional(readOnly = true)
    public List<PriceChangeResponseDTO> getPriceChangesForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return priceChangeRepository.findByProduct(product).stream()
                .map(priceChangeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePriceChange(Long id) {
        priceChangeRepository.deleteById(id);
    }
}
