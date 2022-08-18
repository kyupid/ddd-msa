package com.kyupid.kshop.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderProductInternalReqRes {
    private List<StockAdjustment> stockAdjustmentList;
    private List<Long> orderIdList;

    public OrderProductInternalReqRes(List<StockAdjustment> stockAdjustmentList, List<Long> orderIdList) {
        this.stockAdjustmentList = stockAdjustmentList;
        this.orderIdList = orderIdList;
    }
}
