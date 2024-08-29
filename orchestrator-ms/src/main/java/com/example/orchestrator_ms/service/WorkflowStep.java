package com.example.orchestrator_ms.service;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();

    void process();

    void revert();

}