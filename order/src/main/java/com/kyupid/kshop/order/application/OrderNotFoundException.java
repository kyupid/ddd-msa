package com.kyupid.kshop.order.application;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {

    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        this.orderId = orderId;
    }
}
