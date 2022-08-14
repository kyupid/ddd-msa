package com.kyupid.kshop.product.api.dto;

import com.kyupid.kshop.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseData {
    private Long productId;
    private String name;
    private Integer price;

    @Builder
    public ProductResponseData(Long productId, String name, Integer price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public static ProductResponseData from(Product product) {
        return new ProductResponseData(product.getId(), product.getName(), product.getPrice());
    }
}
