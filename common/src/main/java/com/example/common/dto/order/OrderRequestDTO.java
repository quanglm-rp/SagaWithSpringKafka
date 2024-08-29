package com.example.common.dto.order;

import com.example.common.state.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderRequestDTO {
    private Integer userId;
    private Integer productId;
    private State status;
    private Integer quantity;
    private UUID id;
    private Integer totalPrice;
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
