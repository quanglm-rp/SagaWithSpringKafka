package com.example.order_service.model;

import com.example.common.state.State;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private UUID id = UUID.randomUUID();
    private Integer userId;
    private Integer productId;
    private State status;
    private Integer quantity;
    private Integer totalPrice;
}
