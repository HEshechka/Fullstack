package net.petproject.backend.controller.cart;

import jakarta.validation.Valid;
import net.petproject.backend.dto.cart.CartItemRequestDTO;
import net.petproject.backend.dto.cart.CartResponseDTO;
import net.petproject.backend.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Пример получения userId из контекста аутентификации (заглушка)
    private Long getCurrentUserId() {
        // В реальном приложении: SecurityContextHolder.getContext().getAuthentication().getName();
        // Затем получить ID пользователя из БД
        return 1L; // Заглушка: всегда пользователь с ID 1
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCartForCurrentUser() {
        Long userId = getCurrentUserId();
        CartResponseDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addProductToCart(@Valid @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        Long userId = getCurrentUserId();
        CartResponseDTO updatedCart = cartService.addProductToCart(userId, cartItemRequestDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<CartResponseDTO> updateProductQuantityInCart(@PathVariable Long productId, @RequestParam Integer quantity) {
        Long userId = getCurrentUserId();
        CartResponseDTO updatedCart = cartService.updateProductQuantityInCart(userId, productId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId) {
        Long userId = getCurrentUserId();
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        Long userId = getCurrentUserId();
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
