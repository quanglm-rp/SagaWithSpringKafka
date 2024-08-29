package com.example.order_service.utils;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.dto.order.OrderResponseDTO;
import com.example.order_service.model.Order;
import org.springframework.beans.BeanUtils;

public class OrderMapping {
    public static OrderResponseDTO entityToDto(Order order) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        BeanUtils.copyProperties(order, orderResponseDTO);
        return orderResponseDTO;
    }

    public static Order dtoToEntity(OrderRequestDTO productDto) {
        Order order = new Order();
        BeanUtils.copyProperties(productDto, order);
        return order;
    }
}
