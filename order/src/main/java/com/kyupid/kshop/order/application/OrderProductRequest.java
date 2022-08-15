package com.kyupid.kshop.order.application;

import lombok.Getter;

@Getter
public class OrderProductRequest {
    private Long productId;
    private Integer quantity;

    public OrderProductRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
