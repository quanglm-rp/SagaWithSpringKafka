package com.example.order_service.service;

import com.example.common.dto.order.OrderResponseDTO;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.common.dto.order.OrderRequestDTO;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface IOrderService {
    Mono<OrderResponseDTO> findOrderByOrderId(UUID id) throws OrderNotFoundException, ExecutionException, InterruptedException;

    Flux<OrderResponseDTO> findAllOrders();

    Mono<OrderResponseDTO> createOrder(Mono<OrderRequestDTO> orderRequestDTO);

    Mono<OrderResponseDTO> updateOrder(UUID id, Mono<OrderRequestDTO> orderRequestDTO);

    Mono<Void> deleteOrderById(UUID id);
}
