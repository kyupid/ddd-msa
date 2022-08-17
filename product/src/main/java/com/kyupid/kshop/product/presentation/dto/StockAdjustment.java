package com.kyupid.kshop.product.presentation.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class StockAdjustment {
    private Long productId;
    private Integer quantity;
    private AdjustmentType adjustmentType;
    private Integer pricePerProduct;

    public StockAdjustment() {
    }

    public StockAdjustment(Long productId, Integer pricePerProduct) {
        this.productId = productId;
        this.pricePerProduct = pricePerProduct;
    }
}
