package com.example.order_service.service;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.dto.order.OrderResponseDTO;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.springcloud.SpringCloudStreamPubliser;
import com.example.order_service.model.Order;
import com.example.order_service.repo.OrderRepository;
import com.example.order_service.utils.OrderMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository _orderRepository;
    private final SpringCloudStreamPubliser _springCloudStreamPubliser;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, SpringCloudStreamPubliser springCloudStreamPubliser) {
        this._orderRepository = orderRepository;
        this._springCloudStreamPubliser = springCloudStreamPubliser;
    }

    @Override
    public Mono<OrderResponseDTO> findOrderByOrderId(UUID id) throws ExecutionException, InterruptedException {
        Mono<OrderResponseDTO> order = _orderRepository.findById(id).map(OrderMapping::entityToDto)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(String.format("Order not found. ID: %s", id))));
        return order;
    }

    @Override
    public Flux<OrderResponseDTO> findAllOrders() {
        Flux<OrderResponseDTO> responses = _orderRepository.findAll().map(OrderMapping::entityToDto);
        return responses;
    }

    @Override
    public Mono<OrderResponseDTO> createOrder(Mono<OrderRequestDTO> orderRequestDTO) {
        Mono<Order> orderMono = orderRequestDTO.map(OrderMapping::dtoToEntity).flatMap(_orderRepository::save)
                .flatMap(o -> {
                    OrderRequestDTO orderSend = new OrderRequestDTO();
                    orderSend.setId(o.getId());
                    orderSend.setProductId(o.getProductId());
                    orderSend.setUserId(o.getUserId());
                    orderSend.setStatus(o.getStatus());
                    orderSend.setQuantity(o.getQuantity());
                    orderSend.setTotalPrice(o.getTotalPrice());
                    _springCloudStreamPubliser.send(orderSend);
                    return Mono.just(o);
                });;

        if(orderMono != null) {
            Mono<OrderResponseDTO> response = orderMono.map(OrderMapping::entityToDto);
            return response;
        }
        else
        {
            return Mono.empty();
        }
    }

    @Override
    public Mono<OrderResponseDTO> updateOrder(UUID id, Mono<OrderRequestDTO> orderRequestDTO) {
        return _orderRepository.findById(id)
                .flatMap(user -> orderRequestDTO
                        .map(OrderMapping::dtoToEntity)
                        .doOnNext(u -> u.setId(id)))
                .flatMap(_orderRepository::save)
                .map(OrderMapping::entityToDto);
    }

    @Override
    public Mono<Void> deleteOrderById(UUID id) {
        return _orderRepository.deleteById(id);
    }
}
