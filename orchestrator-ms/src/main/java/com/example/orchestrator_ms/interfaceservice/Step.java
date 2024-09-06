package com.example.orchestrator_ms.interfaceservice;

import com.example.common.dto.order.OrderRequestDTO;
import org.springframework.cloud.stream.function.StreamBridge;

public interface Step {
    void process(OrderRequestDTO orderRequestDTO, StreamBridge streamBridge);
}
