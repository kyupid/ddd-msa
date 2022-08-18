package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderProductInternalReqRes {
    private final List<StockAdjustment> stockAdjustmentList;
    private final List<Long> reservedStockIdList;

    public OrderProductInternalReqRes(List<StockAdjustment> stockAdjustmentList, List<Long> reservedStockIdList) {
        this.stockAdjustmentList = stockAdjustmentList;
        this.reservedStockIdList = reservedStockIdList;
    }
}
