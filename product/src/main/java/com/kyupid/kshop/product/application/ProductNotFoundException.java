package com.kyupid.kshop.product.application;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{

    private final ProductId productId;

    public ProductNotFoundException(Long productId) {
        this.productId = new ProductId(productId);
    }
}
