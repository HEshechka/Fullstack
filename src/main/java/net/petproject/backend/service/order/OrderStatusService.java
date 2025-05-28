package net.petproject.backend.service.order;

import net.petproject.backend.dto.order.OrderStatusDTO;
import net.petproject.backend.mapper.order.OrderStatusMapper;
import net.petproject.backend.model.order.OrderStatus;
import net.petproject.backend.repository.order.OrderStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    public OrderStatusService(OrderStatusRepository orderStatusRepository, OrderStatusMapper orderStatusMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapper = orderStatusMapper;
    }

    @Transactional
    public OrderStatusDTO createOrderStatus(OrderStatusDTO orderStatusDTO) {
        if (orderStatusRepository.findByStatusName(orderStatusDTO.getStatusName()).isPresent()) {
            throw new IllegalArgumentException("Order Status with this name already exists: " + orderStatusDTO.getStatusName());
        }
        // Поскольку OrderStatusDTO универсален для запроса и ответа, создаем сущность вручную или добавляем toEntity в маппер
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatusName(orderStatusDTO.getStatusName());
        OrderStatus savedStatus = orderStatusRepository.save(orderStatus);
        return orderStatusMapper.toDto(savedStatus);
    }

    @Transactional(readOnly = true)
    public OrderStatusDTO getOrderStatusById(Long id) {
        OrderStatus status = orderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + id));
        return orderStatusMapper.toDto(status);
    }

    @Transactional(readOnly = true)
    public List<OrderStatusDTO> getAllOrderStatuses() {
        return orderStatusRepository.findAll().stream()
                .map(orderStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderStatusDTO updateOrderStatus(Long id, OrderStatusDTO orderStatusDTO) {
        OrderStatus existingStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Status not found with ID: " + id));

        if (orderStatusDTO.getStatusName() != null && !existingStatus.getStatusName().equals(orderStatusDTO.getStatusName())) {
            if (orderStatusRepository.findByStatusName(orderStatusDTO.getStatusName()).isPresent()) {
                throw new IllegalArgumentException("Order Status with this name already exists: " + orderStatusDTO.getStatusName());
            }
            existingStatus.setStatusName(orderStatusDTO.getStatusName());
        }
        OrderStatus updatedStatus = orderStatusRepository.save(existingStatus);
        return orderStatusMapper.toDto(updatedStatus);
    }

    @Transactional
    public void deleteOrderStatus(Long id) {
        orderStatusRepository.deleteById(id);
    }
}
