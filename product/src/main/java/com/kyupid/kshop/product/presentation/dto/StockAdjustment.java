package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class StockAdjustment {
    private Long productId;
    private Integer quantity;
    private AdjustmentType adjustmentType;
    private Integer pricePerProduct;
}
