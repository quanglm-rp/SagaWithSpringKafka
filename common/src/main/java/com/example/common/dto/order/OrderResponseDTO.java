package com.example.common.dto.order;

import com.example.common.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderResponseDTO {
    private Integer userId;
    private Integer productId;
    private State status;
    private UUID id;
    private Integer quantity;
    private Integer totalPrice;
}
