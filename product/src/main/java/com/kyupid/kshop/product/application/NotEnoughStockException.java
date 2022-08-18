package com.kyupid.kshop.product.application;

import lombok.Getter;

import java.util.List;

@Getter
public class NotEnoughStockException extends RuntimeException {

    private final ProductIdList productIds;

    public NotEnoughStockException(List<Long> productIds) {
        this.productIds = new ProductIdList(productIds);
    }
}
