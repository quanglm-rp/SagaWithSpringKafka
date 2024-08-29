package com.example.orchestrator_ms.common;

import com.example.common.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderRequestDTO {
    private Integer userId;
    private Integer productId;
    private State status;
    private UUID id;
}