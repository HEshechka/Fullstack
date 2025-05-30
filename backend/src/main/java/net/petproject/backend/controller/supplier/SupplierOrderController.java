package net.petproject.backend.controller.supplier;

import jakarta.validation.Valid;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderResponseDTO;
import net.petproject.backend.service.supplier.SupplierOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-orders")
public class SupplierOrderController {

    private final SupplierOrderService supplierOrderService;

    public SupplierOrderController(SupplierOrderService supplierOrderService) {
        this.supplierOrderService = supplierOrderService;
    }

    @PostMapping
    public ResponseEntity<SupplierOrderResponseDTO> createSupplierOrder(@Valid @RequestBody SupplierOrderRequestDTO supplierOrderRequestDTO) {
        SupplierOrderResponseDTO newSupplierOrder = supplierOrderService.createSupplierOrder(supplierOrderRequestDTO);
        return new ResponseEntity<>(newSupplierOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierOrderResponseDTO> getSupplierOrderById(@PathVariable Long id) {
        SupplierOrderResponseDTO supplierOrder = supplierOrderService.getSupplierOrderById(id);
        return ResponseEntity.ok(supplierOrder);
    }

    @GetMapping
    public ResponseEntity<List<SupplierOrderResponseDTO>> getAllSupplierOrders() {
        List<SupplierOrderResponseDTO> supplierOrders = supplierOrderService.getAllSupplierOrders();
        return ResponseEntity.ok(supplierOrders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierOrderResponseDTO> updateSupplierOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        SupplierOrderResponseDTO updatedOrder = supplierOrderService.updateSupplierOrderStatus(id, newStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping("/{id}/receive")
    public ResponseEntity<SupplierOrderResponseDTO> receiveSupplierOrder(@PathVariable Long id) {
        SupplierOrderResponseDTO receivedOrder = supplierOrderService.receiveSupplierOrder(id);
        return ResponseEntity.ok(receivedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierOrder(@PathVariable Long id) {
        supplierOrderService.deleteSupplierOrder(id);
        return ResponseEntity.noContent().build();
    }
}
