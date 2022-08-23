package com.kyupid.kshop.order.infra;

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
