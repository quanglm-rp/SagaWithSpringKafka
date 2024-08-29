package com.example.common.dto.inventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryRequestDTO {
    private UUID id;
    private Integer productId;
    private Integer amount;
}
