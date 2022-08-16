package com.kyupid.kshop.product.presentation.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class OrderProductDto {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;

    public OrderProductDto() {
    }

    public OrderProductDto(Long productId, Integer pricePerProduct) {
        this.productId = productId;
        this.pricePerProduct = pricePerProduct;
    }
}
