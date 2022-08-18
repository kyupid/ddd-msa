package com.kyupid.kshop.product.application;

import lombok.Getter;

import java.util.List;

@Getter
public class NotEnoughStockException extends RuntimeException {

    private List<Long> productIds;

    public NotEnoughStockException(List<Long> productIds) {
        this.productIds = productIds;
    }
}
