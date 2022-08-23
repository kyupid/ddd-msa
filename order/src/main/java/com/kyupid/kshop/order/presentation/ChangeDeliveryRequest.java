package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import lombok.Getter;

@Getter
public class ChangeDeliveryRequest {
    private DeliveryInfo deliveryInfo;
    private Long ordererId;

    public void setOrdererId(Long ordererId) {
        this.ordererId = ordererId;
    }
}
