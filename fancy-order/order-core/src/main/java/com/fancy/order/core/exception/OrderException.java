package com.fancy.order.core.exception;

import com.fancy.order.core.enums.OrderExceptionEnum;

public class OrderException extends RuntimeException {
    private OrderExceptionEnum orderExceptionEnum;
    public OrderException(OrderExceptionEnum orderExceptionEnum, Exception e) {
        super(e);
        this.orderExceptionEnum = orderExceptionEnum;
    }

    public OrderException(OrderExceptionEnum orderExceptionEnum) {
        this.orderExceptionEnum = orderExceptionEnum;
    }

    public OrderExceptionEnum getOrderExceptionEnum() {
        return orderExceptionEnum;
    }
}
