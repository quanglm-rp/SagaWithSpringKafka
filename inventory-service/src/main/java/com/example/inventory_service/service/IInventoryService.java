package com.example.inventory_service.service;

import com.example.common.dto.inventory.InventoryRequestDTO;
import com.example.common.dto.inventory.InventoryResponeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IInventoryService {
    Flux<InventoryResponeDTO> findAllInventory();
    Mono<InventoryResponeDTO> createInventory(Mono<InventoryRequestDTO> inventoryRequestDTO);
    Mono<InventoryResponeDTO> updateInventory(UUID id, Mono<InventoryRequestDTO> inventoryRequestDTO);
    Mono<Void> deleteInventory(UUID id);
    Mono<InventoryResponeDTO> checkInventoryExist(int product_id);
    Boolean deductInventory(int product_id, int quantity);
    Boolean revertInventory(int product_id, int quantity);
}
