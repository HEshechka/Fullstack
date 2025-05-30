package net.petproject.backend.controller.order;

import jakarta.validation.Valid;
import net.petproject.backend.dto.order.CreateOrderFromCartDTO;
import net.petproject.backend.dto.order.OrderRequestDTO;
import net.petproject.backend.dto.order.OrderResponseDTO;
import net.petproject.backend.service.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Пример получения userId из контекста аутентификации (заглушка)
    private Long getCurrentUserId() {
        // В реальном приложении: SecurityContextHolder.getContext().getAuthentication().getName();
        // Затем получить ID пользователя из БД
        return 1L; // Заглушка: всегда пользователь с ID 1
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Long userId = getCurrentUserId(); // Получаем текущего пользователя
        OrderResponseDTO newOrder = orderService.createOrder(orderRequestDTO, userId);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PostMapping("/from-cart")
    public ResponseEntity<OrderResponseDTO> createOrderFromCart(@Valid @RequestBody CreateOrderFromCartDTO createOrderFromCartDTO) {
        Long userId = getCurrentUserId(); // Получаем текущего пользователя
        OrderResponseDTO newOrder = orderService.createOrderFromCart(createOrderFromCartDTO, userId);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderResponseDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/status/{statusId}")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(@PathVariable Long orderId, @PathVariable Long statusId) {
        OrderResponseDTO updatedOrder = orderService.updateOrderStatus(orderId, statusId);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
