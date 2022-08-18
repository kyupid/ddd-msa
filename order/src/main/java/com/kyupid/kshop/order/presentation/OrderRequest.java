package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.infra.StockAdjustment;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private List<StockAdjustment> stockAdjustment;
    private Long ordererMemberId;
    private DeliveryInfo deliveryInfo;

    public void setOrdererId(Long ordererMemberId) {
        this.ordererMemberId = ordererMemberId;
    }
}
