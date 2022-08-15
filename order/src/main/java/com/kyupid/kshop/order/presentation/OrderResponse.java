package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderProduct;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {
    private List<OrderProduct> orderProductList;
    private Long ordererId;
    private DeliveryInfo deliveryInfo;

    public OrderResponse(List<OrderProduct> orderProductList, Long ordererId, DeliveryInfo deliveryInfo) {
        this.orderProductList = orderProductList;
        this.ordererId = ordererId;
        this.deliveryInfo = deliveryInfo;
    }

    public static OrderResponse from(Order o) {
        return new OrderResponse(o.getOrderProductList(), o.getOrderId(), o.getDeliveryInfo());
    }
}
