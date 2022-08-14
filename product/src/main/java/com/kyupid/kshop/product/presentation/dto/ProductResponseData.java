package com.kyupid.kshop.product.presentation.dto;

import com.kyupid.kshop.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseData {
    private Long productId;
    private String name;
    private Integer price;
    private Integer quantityLeft;

    @Builder
    public ProductResponseData(Long productId, String name, Integer price, Integer quantityLeft) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityLeft = quantityLeft;
    }

    public static ProductResponseData from(Product p) {
        return ProductResponseData.builder()
                .productId(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .quantityLeft(p.getQuantityLeft())
                .build();
    }
}
