package com.kyupid.kshop.order.presentation.dto;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.domain.OrderStatus;
import com.kyupid.kshop.order.infra.StockAdjustment;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private List<StockAdjustment> stockAdjustmentList;
    private Long ordererMemberId;
    private DeliveryInfo deliveryInfo;

    public void setOrdererId(Long ordererMemberId) {
        this.ordererMemberId = ordererMemberId;
    }
}
