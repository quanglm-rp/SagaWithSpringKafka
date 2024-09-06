package com.example.orchestrator_ms.steps;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.state.State;
import com.example.orchestrator_ms.interfaceservice.Step;
import org.springframework.cloud.stream.function.StreamBridge;

public class PaymentStep implements Step {

    @Override
    public void process(OrderRequestDTO orderRequestDTO, StreamBridge streamBridge) {
        if(orderRequestDTO.getStatus() == State.IN_STOCK) {
            streamBridge.send("order-check-payment-topic", orderRequestDTO);
        }
        if (orderRequestDTO.getStatus() == State.OUT_OF_STOCK) {
            streamBridge.send("order-revert-payment-topic", orderRequestDTO);
        }
    }
}
