package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.OrderProductRequest;
import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderProduct;
import com.kyupid.kshop.order.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderRequest {
    private List<OrderProductRequest> orderProductList;
    private Long ordererId;
    private DeliveryInfo deliveryInfo;

    public void setOrdererId(Long ordererId) {
        this.ordererId = ordererId;
    }

    public Order toEntity() {
        return Order.builder()
//                .orderProductList(orderProductList)
                .ordererMemberId(ordererId)
                .orderStatus(OrderStatus.PAYMENT_WAITING)
                .deliveryInfo(deliveryInfo)
                .orderDate(LocalDateTime.now())
                .build();
    }
}
