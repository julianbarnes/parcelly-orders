package com.parcelly.orders.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Maps entities to OrderResponse inside the transaction, so no entity leaves this class
// (open-in-view is off, so lazy associations would fail if mapped later in the controller).
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::from)
                .toList();
    }

    public OrderResponse getById(Long id) {
        return orderRepository.findById(id)
                .map(OrderResponse::from)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public OrderResponse create(String customerName) {
        return OrderResponse.from(orderRepository.save(new Order(customerName)));
    }
}
