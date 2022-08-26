package com.kyupid.kshop.order.presentation.dto;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {
    private List<OrderProductResponse> orderProductList;
    private Long ordererId;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private DeliveryInfo deliveryInfo;

    public OrderResponse(List<OrderProductResponse> orderProductList, Long ordererId, Order order) {
        this.orderProductList = orderProductList;
        this.ordererId = ordererId;
        this.deliveryInfo = order.getDeliveryInfo();
        this.orderStatus = order.getOrderStatus();
        this.orderDate = order.getOrderDate();
    }
}
