package com.kyupid.kshop.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductInternalReqRes {
    private List<StockAdjustment> stockAdjustmentList;
}
