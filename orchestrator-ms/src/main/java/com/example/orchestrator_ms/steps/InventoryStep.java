package com.example.orchestrator_ms.steps;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.state.State;
import com.example.orchestrator_ms.interfaceservice.Step;
import org.springframework.cloud.stream.function.StreamBridge;

public class InventoryStep implements Step {

    private StreamBridge streamBridge;

    public InventoryStep(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void excute(OrderRequestDTO orderRequestDTO) {
        if(orderRequestDTO.getStatus() == State.ORDER_CREATED){
            streamBridge.send("order-check-inventory-topic", orderRequestDTO);
        }
        if(orderRequestDTO.getStatus() == State.ORDER_CANCELED){
            streamBridge.send("order-revert-payment-topic", orderRequestDTO);
        }
    }
}
