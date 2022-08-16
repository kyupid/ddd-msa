package com.kyupid.kshop.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductWithPrice {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
}
