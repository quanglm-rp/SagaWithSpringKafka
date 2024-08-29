package com.example.orchestrator_ms.common;

import com.example.common.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderResponseDTO {
    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private Double amount;
    private State status;
}