package com.example.orchestrator_ms.service;

import com.example.orchestrator_ms.common.InventoryRequestDTO;
import com.example.orchestrator_ms.common.OrchestratorRequestDTO;
import com.example.orchestrator_ms.common.PaymentRequestDTO;
import com.example.orchestrator_ms.steps.InventoryStep;
import com.example.orchestrator_ms.steps.PaymentStep;

import java.util.List;

public class OrchestratorService {

    public void orderProduct(final OrchestratorRequestDTO requestDTO) {
        Workflow orderWorkflow = getOrderWorkflow(requestDTO);
    }

    public Workflow getOrderWorkflow(OrchestratorRequestDTO requestDTO) {
        WorkflowStep inventoryStep = new InventoryStep(getInventoryRequestDTO(requestDTO));
        WorkflowStep paymentStep = new PaymentStep(getPaymentRequestDTO(requestDTO));
        //WorkflowStep inventoryStep = new InventoryStep(inventoryClient, getInventoryRequestDTO(requestDTO));
        return new OrderWorkflow(List.of(inventoryStep,paymentStep));
    }


    private PaymentRequestDTO getPaymentRequestDTO(OrchestratorRequestDTO requestDTO) {
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setUserId(requestDTO.getUserId());
        paymentRequestDTO.setAmount(requestDTO.getAmount());
        paymentRequestDTO.setOrderId(requestDTO.getOrderId());
        return paymentRequestDTO;
    }

    private InventoryRequestDTO getInventoryRequestDTO(OrchestratorRequestDTO requestDTO) {
        InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO();
        inventoryRequestDTO.setUserId(requestDTO.getUserId());
        inventoryRequestDTO.setProductId(requestDTO.getProductId());
        inventoryRequestDTO.setOrderId(requestDTO.getOrderId());
        return inventoryRequestDTO;
    }
}
