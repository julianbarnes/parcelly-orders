package com.parcelly.orders.order;

import java.time.Instant;

/** Public representation of an order. The API contract; independent of the {@code Order} entity. */
public record OrderResponse(
        Long id,
        String customerName,
        OrderStatus status,
        Instant createdAt
) {

    static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getStatus(),
                order.getCreatedAt());
    }
}
