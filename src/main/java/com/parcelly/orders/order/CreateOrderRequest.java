package com.parcelly.orders.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Body of {@code POST /api/orders}. Only the fields a client may set. */
public record CreateOrderRequest(
        @NotBlank
        @Size(max = 255)
        String customerName
) {
}
