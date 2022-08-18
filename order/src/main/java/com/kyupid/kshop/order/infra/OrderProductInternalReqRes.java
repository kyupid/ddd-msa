package com.kyupid.kshop.order.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class OrderProductInternalReqRes {
    private List<StockAdjustment> stockAdjustmentList;
    private List<Long> reservedStockIdList;

    public OrderProductInternalReqRes(List<StockAdjustment> stockAdjustmentList) {
        this.stockAdjustmentList = stockAdjustmentList;
        this.reservedStockIdList = null;
    }
}
