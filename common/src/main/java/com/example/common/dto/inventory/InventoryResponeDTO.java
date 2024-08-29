package com.example.common.dto.inventory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryResponeDTO {
    private UUID id;
    private Integer productId;
    private Integer quantity;
}
