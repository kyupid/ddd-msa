package com.kyupid.kshop.order.application;

import lombok.Getter;

@Getter
public class OrderProductDto {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
}
