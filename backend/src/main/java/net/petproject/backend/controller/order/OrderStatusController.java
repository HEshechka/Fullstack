package net.petproject.backend.controller.order;

import jakarta.validation.Valid;
import net.petproject.backend.dto.order.OrderStatusDTO;
import net.petproject.backend.service.order.OrderStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-statuses")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PostMapping
    public ResponseEntity<OrderStatusDTO> createOrderStatus(@Valid @RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO newStatus = orderStatusService.createOrderStatus(orderStatusDTO);
        return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatusById(@PathVariable Long id) {
        OrderStatusDTO status = orderStatusService.getOrderStatusById(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping
    public ResponseEntity<List<OrderStatusDTO>> getAllOrderStatuses() {
        List<OrderStatusDTO> statuses = orderStatusService.getAllOrderStatuses();
        return ResponseEntity.ok(statuses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@PathVariable Long id, @Valid @RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO updatedStatus = orderStatusService.updateOrderStatus(id, orderStatusDTO);
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable Long id) {
        orderStatusService.deleteOrderStatus(id);
        return ResponseEntity.noContent().build();
    }
}
