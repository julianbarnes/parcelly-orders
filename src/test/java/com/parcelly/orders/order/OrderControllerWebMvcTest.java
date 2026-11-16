package com.parcelly.orders.order;

import com.parcelly.orders.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Web slice: controller + JSON + validation only, with the service mocked. No database.
@WebMvcTest(OrderController.class)
@Import(SecurityConfig.class)
class OrderControllerWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    OrderService orderService;

    @Test
    void createReturns201WithLocationAndBody() throws Exception {
        when(orderService.create("Ada Lovelace")).thenReturn(new OrderResponse(
                42L, "Ada Lovelace", OrderStatus.CREATED, Instant.parse("2026-11-16T10:00:00Z")));

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerName": "Ada Lovelace"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/orders/42"))
                .andExpect(jsonPath("$.id").value(42))
                .andExpect(jsonPath("$.customerName").value("Ada Lovelace"))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.createdAt").value("2026-11-16T10:00:00Z"));
    }

    @Test
    void createIgnoresFieldsClientsMayNotSet() throws Exception {
        when(orderService.create("Ada")).thenReturn(new OrderResponse(
                1L, "Ada", OrderStatus.CREATED, Instant.parse("2026-11-16T10:00:00Z")));

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerName": "Ada", "id": 999, "status": "CANCELLED"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void createRejectsBlankCustomerName() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"customerName": "   "}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Invalid request"))
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].field").value("customerName"))
                .andExpect(jsonPath("$.errors[0].message").value("must not be blank"));
        verifyNoInteractions(orderService);
    }

    @Test
    void createRejectsMissingCustomerName() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("customerName"));
        verifyNoInteractions(orderService);
    }

    @Test
    void createRejectsCustomerNameLongerThan255() throws Exception {
        String tooLong = "x".repeat(256);
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\": \"" + tooLong + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("customerName"))
                .andExpect(jsonPath("$.errors[0].message").value("size must be between 0 and 255"));
        verifyNoInteractions(orderService);
    }

    @Test
    void createRejectsMalformedJsonWithProblemDetail() throws Exception {
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{not json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400));
        verifyNoInteractions(orderService);
    }

    @Test
    void getReturns404ProblemDetailForUnknownOrder() throws Exception {
        when(orderService.getById(7L)).thenThrow(new OrderNotFoundException(7L));

        mockMvc.perform(get("/api/orders/{id}", 7))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Order not found"))
                .andExpect(jsonPath("$.detail").value("Order 7 was not found."))
                .andExpect(jsonPath("$.orderId").value(7));
    }
}
