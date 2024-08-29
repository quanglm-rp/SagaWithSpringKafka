package com.example.inventory_service.utils;

import com.example.common.dto.inventory.InventoryRequestDTO;
import com.example.common.dto.inventory.InventoryResponeDTO;
import com.example.inventory_service.model.Inventory;
import org.springframework.beans.BeanUtils;

public class InventoryMapping {
    public static InventoryResponeDTO entityToDto(Inventory inventory) {
        InventoryResponeDTO inventoryResponeDTO = new InventoryResponeDTO();
        BeanUtils.copyProperties(inventory, inventoryResponeDTO);
        return inventoryResponeDTO;
    }

    public static Inventory dtoToEntity(InventoryRequestDTO productDto) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(productDto, inventory);
        return inventory;
    }

    public static Inventory dtoToEntity(InventoryResponeDTO productDto) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(productDto, inventory);
        return inventory;
    }
}
