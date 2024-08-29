package com.example.common.state;

public enum State {
    //order state
    ORDER_CREATED,
    ORDER_SUCCESS,
    ORDER_UPDATED,
    ORDER_CANCELED,

    //payment state
    SUCCESS_PAYMENT,
    FAILED_PAYMENT,

    //inventory state
    IN_STOCK,
    OUT_OF_STOCK,
}
