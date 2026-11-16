package com.parcelly.orders.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    void createSavesNewOrderInCreatedStatus() {
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));

        OrderResponse created = orderService.create("Ada Lovelace");

        ArgumentCaptor<Order> saved = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(saved.capture());
        assertThat(saved.getValue().getCustomerName()).isEqualTo("Ada Lovelace");
        assertThat(saved.getValue().getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(created.customerName()).isEqualTo("Ada Lovelace");
        assertThat(created.status()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void getByIdThrowsWhenOrderDoesNotExist() {
        when(orderRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.getById(7L))
                .isInstanceOf(OrderNotFoundException.class);
    }
}
