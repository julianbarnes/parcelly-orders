package com.parcelly.orders.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/** Thrown when no order exists for an id. Spring MVC renders it as a 404 problem+json body. */
public class OrderNotFoundException extends ErrorResponseException {

    public OrderNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, problemFor(id), null);
    }

    private static ProblemDetail problemFor(Long id) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Order " + id + " was not found.");
        problem.setTitle("Order not found");
        problem.setProperty("orderId", id);
        return problem;
    }
}
