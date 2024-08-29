package com.example.inventory_service.repo;

import com.example.common.dto.inventory.InventoryResponeDTO;
import com.example.inventory_service.model.Inventory;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface InventoryRepository extends R2dbcRepository<Inventory, UUID> {
    Mono<InventoryResponeDTO> findByProductId(int productId);
}
