package com.example.orchestrator_ms.springcloud;

import com.example.common.dto.order.OrderRequestDTO;
import com.example.orchestrator_ms.interfaceservice.Step;
import com.example.orchestrator_ms.service.WorkflowStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;

@Configuration
public class SpringCloudStreamConsumerOrchestrator {

    @Autowired
    private StreamBridge streamBridge;

    @Bean
    public Consumer<OrderRequestDTO> orderBinding() {
        return orderRequestDTO -> {
            System.out.println(orderRequestDTO);

            Step step = WorkflowStep.steps.get(orderRequestDTO.getStatus());
            step.process(orderRequestDTO, streamBridge);

//            //send inventory success
//            if(OrderRequestDTO.getStatus() == State.ORDER_CREATED)
//            {
//                streamBridge.send("order-check-inventory-topic", OrderRequestDTO);
//            }
//            //send payment success
//            if(OrderRequestDTO.getStatus() == State.IN_STOCK)
//            {
//                streamBridge.send("order-check-payment-topic", OrderRequestDTO);
//            }
//            //send notification success
//            if(OrderRequestDTO.getStatus() == State.ORDER_SUCCESS)
//            {
//                streamBridge.send("order-notification-topic", OrderRequestDTO);
//            }
//
//            //revert
//            if(OrderRequestDTO.getStatus() == State.FAILED_PAYMENT)
//            {
//                OrderRequestDTO.setStatus(State.ORDER_CANCELED);
//                streamBridge.send("order-check-payment-topic", OrderRequestDTO);
//                streamBridge.send("order-check-inventory-topic", OrderRequestDTO);
//                streamBridge.send("order-notification-topic", OrderRequestDTO);
//            }
//
//            if(OrderRequestDTO.getStatus() == State.OUT_OF_STOCK)
//            {
//                OrderRequestDTO.setStatus(State.ORDER_CANCELED);
//                streamBridge.send("order-check-inventory-topic", OrderRequestDTO);
//                streamBridge.send("order-notification-topic", OrderRequestDTO);
//            }
//
//            if(OrderRequestDTO.getStatus() == State.ORDER_CANCELED)
//            {
//                streamBridge.send("order-notification-topic", OrderRequestDTO);
//            }
        };
    }
}
