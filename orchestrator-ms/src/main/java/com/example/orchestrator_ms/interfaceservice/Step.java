package com.example.orchestrator_ms.interfaceservice;

import com.example.common.dto.order.OrderRequestDTO;

public interface Step {
    void excute(OrderRequestDTO orderRequestDTO);
}
