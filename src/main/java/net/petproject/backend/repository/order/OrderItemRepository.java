package net.petproject.backend.repository.order;

import net.petproject.backend.model.order.Order;
import net.petproject.backend.model.order.OrderItem;
import net.petproject.backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
}
