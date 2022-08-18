package com.kyupid.kshop.order.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ConfirmStockRequest {
    private List<Long> reservedStockIdList;

    public ConfirmStockRequest(List<Long> reservedStockIdList) {
        this.reservedStockIdList = reservedStockIdList;
    }
}
