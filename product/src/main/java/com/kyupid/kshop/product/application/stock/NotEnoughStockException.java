package com.kyupid.kshop.product.application.stock;

import com.kyupid.kshop.product.application.ProductIdList;
import lombok.Getter;

import java.util.List;

@Getter
public class NotEnoughStockException extends RuntimeException {

    private final ProductIdList productIds;

    public NotEnoughStockException(List<Long> productIds) {
        this.productIds = new ProductIdList(productIds);
    }
}
