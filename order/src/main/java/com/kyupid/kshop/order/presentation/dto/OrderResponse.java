package com.kyupid.kshop.order.presentation.dto;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {
    private List<OrderProductResponse> orderProductList;
    private Long ordererId;
    private DeliveryInfo deliveryInfo;

    public OrderResponse(List<OrderProductResponse> orderProductList, Long ordererId, DeliveryInfo deliveryInfo) {
        this.orderProductList = orderProductList;
        this.ordererId = ordererId;
        this.deliveryInfo = deliveryInfo;
    }
}
