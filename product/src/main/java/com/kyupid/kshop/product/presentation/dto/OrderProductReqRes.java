package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderProductReqRes {
    private final List<StockAdjustment> stockAdjustmentList;

    public OrderProductReqRes(List<StockAdjustment> stockAdjustmentList) {
        this.stockAdjustmentList = stockAdjustmentList;
    }
}
