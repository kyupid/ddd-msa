package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.domain.DeliveryInfo;
import com.kyupid.kshop.order.domain.OrderProduct;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {
    private List<OrderProduct> orderProductList;
    private Long ordererMemberId;
    private DeliveryInfo deliveryInfo;

}
