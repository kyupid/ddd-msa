package com.kyupid.kshop.order.infra;

import lombok.Getter;

@Getter
public class StockAdjustment {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
}
