package com.example.orchestrator_ms.steps;

import com.example.orchestrator_ms.common.InventoryRequestDTO;
import com.example.orchestrator_ms.common.PaymentRequestDTO;
import com.example.orchestrator_ms.service.WorkflowStep;
import com.example.orchestrator_ms.service.WorkflowStepStatus;

public class PaymentStep implements WorkflowStep {
    private final PaymentRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(PaymentRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }
    @Override
    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public void process() {

    }

    @Override
    public void revert() {

    }
}
