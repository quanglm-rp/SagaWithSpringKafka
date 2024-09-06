package com.example.inventory_service.service;

import com.example.common.dto.inventory.InventoryRequestDTO;
import com.example.common.dto.inventory.InventoryResponeDTO;
import com.example.inventory_service.model.Inventory;
import com.example.inventory_service.repo.InventoryRepository;
import com.example.inventory_service.utils.InventoryMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class InventoryServiceimpl implements IInventoryService {

    private boolean success = true;
    @Autowired
    private InventoryRepository _inventoryRepository;

    @Autowired
    public InventoryServiceimpl(InventoryRepository inventoryRepository) {
        this._inventoryRepository = inventoryRepository;
    }

    @Override
    public Flux<InventoryResponeDTO> findAllInventory() {
        Flux<InventoryResponeDTO> responses = _inventoryRepository.findAll().map(InventoryMapping::entityToDto);
        return responses;
    }

    @Override
    public Mono<InventoryResponeDTO> createInventory(Mono<InventoryRequestDTO> inventoryRequestDTO) {
        Mono<Inventory> orderMono = inventoryRequestDTO.map(InventoryMapping::dtoToEntity)
                .flatMap(_inventoryRepository::save).flatMap(i -> {
                    InventoryRequestDTO inventorySend = new InventoryRequestDTO();
                    inventorySend.setId(i.getId());
                    inventorySend.setProductId(i.getProductId());
                    inventorySend.setAmount(i.getAmount());
                    //_springCloudStreamPubliser.send(orderSend);
                    return Mono.just(i);
                });
        ;

        if (orderMono != null) {
            Mono<InventoryResponeDTO> response = orderMono.map(InventoryMapping::entityToDto);
            return response;
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Mono<InventoryResponeDTO> updateInventory(UUID id, Mono<InventoryRequestDTO> inventoryRequestDTO) {
        return _inventoryRepository.findById(id)
                .flatMap(order -> inventoryRequestDTO.map(InventoryMapping::dtoToEntity).doOnNext(u -> u.setId(id)))
                .flatMap(_inventoryRepository::save).map(InventoryMapping::entityToDto);
    }

    @Override
    public Mono<Void> deleteInventory(UUID id) {
        return _inventoryRepository.deleteById(id);
    }

    @Override
    public Mono<InventoryResponeDTO> checkInventoryExist(int product_id) {
        return _inventoryRepository.findByProductId(product_id);
    }

    @Override
    public Boolean deductInventory(int product_id, int quantity) {

        try {
            Mono<Inventory> a = _inventoryRepository.findByProductId(product_id).map(InventoryMapping::dtoToEntity);
            _inventoryRepository.findByProductId(product_id).map(InventoryMapping::dtoToEntity)
                    .flatMap(savedInventory -> _inventoryRepository.findById(savedInventory.getId()).doOnNext(u -> {
                        if (u.getAmount() > quantity + 1) {
                            System.out.println("Success");
                            u.setId(savedInventory.getId());
                            u.setAmount(u.getAmount() - quantity);
                        }
                        else
                        {
                            System.out.println("Fail");
                            success = false;
                        }
                    }).flatMap(_inventoryRepository::save))
                    .subscribe(resultValue -> {
                                if (resultValue.getAmount() > quantity + 1) {
                                    System.out.println("Success");
                                    success = true;
                                }
                                else
                                {
                                    System.out.println("Fail");
                                    success = false;
                                }
                            },
                            error -> System.err.println("Error: " + error)
                    );
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            success = false;
        }
        return success;
    }

    @Override
    public Boolean revertInventory(int product_id, int quantity) {
        try {
            _inventoryRepository.findByProductId(product_id).map(InventoryMapping::dtoToEntity)
                    .flatMap(savedInventory -> _inventoryRepository.findById(savedInventory.getId()).doOnNext(u -> {
                        u.setId(savedInventory.getId());
                        u.setAmount(u.getAmount() + quantity);
                    }).flatMap(_inventoryRepository::save))
                    .subscribe(resultValue -> System.out.println("Inventory updated: " + resultValue),
                            error -> System.err.println("Error: " + error)
                    );
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            success = false;
        }
        return success;
    }
}
