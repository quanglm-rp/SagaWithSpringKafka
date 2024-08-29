package com.example.order_service.repo;

import com.example.order_service.model.Order;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, UUID> {
}
