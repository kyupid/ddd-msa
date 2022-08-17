package com.kyupid.kshop.product.presentation.dto;

import com.kyupid.kshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseData {
    private Long productId;
    private String name;
    private Integer price;
    private Integer stock;

    @Builder
    public ProductResponseData(Long productId, String name, Integer price, Integer stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static ProductResponseData from(Product p) {
        return ProductResponseData.builder()
                .productId(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .stock(p.getStock())
                .build();
    }
}
