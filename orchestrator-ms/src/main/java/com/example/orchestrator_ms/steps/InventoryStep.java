package com.example.orchestrator_ms.steps;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.common.state.State;
import com.example.orchestrator_ms.interfaceservice.Step;
import org.springframework.cloud.stream.function.StreamBridge;

public class InventoryStep implements Step {


    @Override
    public void process(OrderRequestDTO orderRequestDTO, StreamBridge streamBridge) {
        if(orderRequestDTO.getStatus() == State.ORDER_CREATED){
            streamBridge.send("order-check-inventory-topic", orderRequestDTO);
        }
        if(orderRequestDTO.getStatus() == State.ORDER_CANCELED){
            streamBridge.send("order-revert-inventory-topic", orderRequestDTO);
        }
    }
}
