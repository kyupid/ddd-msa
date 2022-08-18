package com.kyupid.kshop.product.application;

import lombok.Getter;

@Getter
public class ProductId {
    private Long productId;

    public ProductId(Long productId) {
        this.productId = productId;
    }
}
