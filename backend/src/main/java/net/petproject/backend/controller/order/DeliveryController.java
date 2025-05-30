package net.petproject.backend.controller.order;

import jakarta.validation.Valid;
import net.petproject.backend.dto.order.delivery.DeliveryRequestDTO;
import net.petproject.backend.dto.order.delivery.DeliveryResponseDTO;
import net.petproject.backend.service.order.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/orders/{orderId}") // Доставка связана с заказом
    public ResponseEntity<DeliveryResponseDTO> createDeliveryForOrder(@PathVariable Long orderId, @Valid @RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryResponseDTO newDelivery = deliveryService.createDeliveryForOrder(orderId, deliveryRequestDTO);
        return new ResponseEntity<>(newDelivery, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(@PathVariable Long id) {
        DeliveryResponseDTO delivery = deliveryService.getDeliveryById(id);
        return ResponseEntity.ok(delivery);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<DeliveryResponseDTO> getDeliveryByOrderId(@PathVariable Long orderId) {
        DeliveryResponseDTO delivery = deliveryService.getDeliveryByOrderId(orderId);
        return ResponseEntity.ok(delivery);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryResponseDTO> updateDelivery(@PathVariable Long id, @Valid @RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        DeliveryResponseDTO updatedDelivery = deliveryService.updateDelivery(id, deliveryRequestDTO);
        return ResponseEntity.ok(updatedDelivery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }
}
