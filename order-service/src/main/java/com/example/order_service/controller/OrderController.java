package com.example.order_service.controller;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.dto.order.OrderResponseDTO;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.model.Order;
import com.example.order_service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.common.util.ObjectToMono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("order")
public class OrderController {


    private final IOrderService _orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this._orderService = orderService;
    }

    @GetMapping("/{id}")
    Mono<ResponseEntity<OrderResponseDTO>> findOrderById(@PathVariable UUID id)
            throws OrderNotFoundException, ExecutionException, InterruptedException {
        return _orderService.findOrderByOrderId(id)
                .map(order -> ResponseEntity.ok(order));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    Flux<OrderResponseDTO> findAllOrders() {
        return _orderService.findAllOrders();
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<ResponseEntity<OrderResponseDTO>> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return _orderService.createOrder(ObjectToMono.toMono(orderRequestDTO))
                .map(savedOrder -> ResponseEntity.status(HttpStatus.CREATED).body(savedOrder));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Mono<ResponseEntity<OrderResponseDTO>> updateOrder(@PathVariable UUID id, @RequestBody OrderRequestDTO orderRequestDTO) {
        return _orderService.updateOrder(id,ObjectToMono.toMono(orderRequestDTO))
                .map(savedOrder -> ResponseEntity.status(HttpStatus.CREATED).body(savedOrder));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Mono<Void> deleteOrderById(@PathVariable UUID id) {
        return _orderService.deleteOrderById(id);
    }
}
