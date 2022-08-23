package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.presentation.dto.AdjustmentType;
import lombok.Getter;

@Getter
public class StockAdjustment {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
    private AdjustmentType adjustmentType;
}
