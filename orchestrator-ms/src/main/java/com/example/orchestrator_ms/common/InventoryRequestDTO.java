package com.example.orchestrator_ms.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryRequestDTO {
    private Integer userId;
    private Integer productId;
    private UUID orderId;

}