package com.example.orchestrator_ms.steps;

import com.example.orchestrator_ms.common.InventoryRequestDTO;
import com.example.orchestrator_ms.service.WorkflowStep;
import com.example.orchestrator_ms.service.WorkflowStepStatus;

public class InventoryStep implements WorkflowStep {

    private final InventoryRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public InventoryStep(InventoryRequestDTO requestDTO) {
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
