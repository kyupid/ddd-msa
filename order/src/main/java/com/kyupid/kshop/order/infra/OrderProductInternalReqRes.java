package com.kyupid.kshop.order.infra;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderProductInternalReqRes {
    private final List<StockAdjustment> stockAdjustmentList;
    private final List<Long> reservedStockIdList;

    public OrderProductInternalReqRes(List<StockAdjustment> stockAdjustmentList) {
        this.stockAdjustmentList = stockAdjustmentList;
        this.reservedStockIdList = null;
    }
}
