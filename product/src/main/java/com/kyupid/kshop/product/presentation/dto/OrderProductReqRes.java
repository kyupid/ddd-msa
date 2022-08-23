package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderProductReqRes {
    private List<StockAdjustment> stockAdjustmentList;

    public OrderProductReqRes(List<StockAdjustment> stockAdjustmentList) {
        this.stockAdjustmentList = stockAdjustmentList;
    }
}
