package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.infra.StockAdjustment;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private List<StockAdjustment> orderProductList;
    private Long ordererId;
    private DeliveryInfo deliveryInfo;

    public void setOrdererId(Long ordererId) {
        this.ordererId = ordererId;
    }
}
