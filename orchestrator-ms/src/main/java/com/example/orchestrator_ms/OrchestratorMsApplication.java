package com.example.orchestrator_ms;

import com.example.orchestrator_ms.interfaceservice.Step;
import com.example.orchestrator_ms.service.WorkflowStep;
import com.example.orchestrator_ms.steps.InventoryStep;
import com.example.orchestrator_ms.steps.PaymentStep;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class OrchestratorMsApplication {

	public static void main(String[] args) {
		WorkflowStep workflowStep = new WorkflowStep();
		workflowStep.initSteps();
		SpringApplication.run(OrchestratorMsApplication.class, args);
	}
}
