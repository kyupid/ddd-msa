package com.kyupid.kshop.product.application;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductIdList {
    private List<Long> productIds;

    public ProductIdList(List<Long> productIds) {
        this.productIds = productIds;
    }
}
