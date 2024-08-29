package com.example.inventory_service.controller;

import com.example.common.dto.inventory.InventoryResponeDTO;
import com.example.inventory_service.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private IInventoryService _inventoryService;

    @GetMapping("/{productId}")
    Mono<InventoryResponeDTO> checkInventory(@PathVariable int productId )
    {
        return _inventoryService.checkInventoryExist(productId) ;
    }

    @PostMapping("/deduct/{productId}/{quantity}")
    Boolean deductInventory(@PathVariable int productId,@PathVariable int quantity)
    {
        return _inventoryService.deductInventory(productId, quantity) == true ? true : false;
    }

    @PostMapping("/revert/{productId}/{quantity}")
    Boolean revertInventory(@PathVariable int productId,@PathVariable int quantity)
    {
        return _inventoryService.revertInventory(productId, quantity) == true ? true : false;
    }
}
