package net.petproject.backend.service.order;

import net.petproject.backend.dto.order.delivery.DeliveryRequestDTO;
import net.petproject.backend.dto.order.delivery.DeliveryResponseDTO;
import net.petproject.backend.mapper.order.DeliveryMapper;
import net.petproject.backend.model.order.Delivery;
import net.petproject.backend.model.order.Order;
import net.petproject.backend.repository.order.DeliveryRepository;
import net.petproject.backend.repository.order.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Transactional
    public DeliveryResponseDTO createDeliveryForOrder(Long orderId, DeliveryRequestDTO deliveryRequestDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        if (deliveryRepository.findByOrder(order).isPresent()) {
            throw new IllegalArgumentException("Delivery already exists for order with ID: " + orderId);
        }

        Delivery delivery = deliveryMapper.toEntity(deliveryRequestDTO);
        delivery.setOrder(order);
        delivery.setStatus(deliveryRequestDTO.getStatus() != null ? deliveryRequestDTO.getStatus() : "PENDING"); // Изначальный статус доставки
        // deliveryDate может быть указан или быть null до actual доставки
        delivery.setDeliveryDate(deliveryRequestDTO.getDeliveryDate()); // Может быть null при создании

        Delivery savedDelivery = deliveryRepository.save(delivery);
        order.setDelivery(savedDelivery); // Связываем заказ с доставкой
        orderRepository.save(order); // Сохраняем обновленный заказ

        return deliveryMapper.toDto(savedDelivery);
    }

    @Transactional(readOnly = true)
    public DeliveryResponseDTO getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with ID: " + id));
        return deliveryMapper.toDto(delivery);
    }

    @Transactional(readOnly = true)
    public DeliveryResponseDTO getDeliveryByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        Delivery delivery = deliveryRepository.findByOrder(order)
                .orElseThrow(() -> new RuntimeException("Delivery not found for order with ID: " + orderId));
        return deliveryMapper.toDto(delivery);
    }

    @Transactional
    public DeliveryResponseDTO updateDelivery(Long id, DeliveryRequestDTO deliveryRequestDTO) {
        Delivery existingDelivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with ID: " + id));

        if (deliveryRequestDTO.getDeliveryDate() != null) {
            existingDelivery.setDeliveryDate(deliveryRequestDTO.getDeliveryDate());
        }
        if (deliveryRequestDTO.getStatus() != null) {
            existingDelivery.setStatus(deliveryRequestDTO.getStatus());
        }
        if (deliveryRequestDTO.getTrackingNumber() != null) {
            existingDelivery.setTrackingNumber(deliveryRequestDTO.getTrackingNumber());
        }
        if (deliveryRequestDTO.getCarrier() != null) {
            existingDelivery.setCarrier(deliveryRequestDTO.getCarrier());
        }

        Delivery updatedDelivery = deliveryRepository.save(existingDelivery);
        return deliveryMapper.toDto(updatedDelivery);
    }

    @Transactional
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
