package com.example.orchestrator_ms.service;

import com.example.common.state.State;
import com.example.orchestrator_ms.interfaceservice.Step;
import com.example.orchestrator_ms.steps.InventoryStep;
import com.example.orchestrator_ms.steps.PaymentStep;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.HashMap;

public class WorkflowStep {
    public static HashMap<State, Step> steps = new HashMap<State, Step>();
    private StreamBridge streamBridge;

    public WorkflowStep() {

    }

    public WorkflowStep(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public HashMap<State, Step> initSteps() {
        steps.put(State.ORDER_CREATED, new InventoryStep(streamBridge));
        steps.put(State.ORDER_CANCELED, new InventoryStep(streamBridge));
        steps.put(State.IN_STOCK, new PaymentStep(streamBridge));
        steps.put(State.OUT_OF_STOCK, new PaymentStep(streamBridge));
        return steps;
    }
}