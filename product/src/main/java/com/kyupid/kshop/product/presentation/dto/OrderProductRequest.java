package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;

@Getter
public class OrderProductRequest {
    private Long productId;
    private Integer quantity;
}
