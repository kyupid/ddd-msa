package com.kyupid.kshop.order.presentation.dto;

import com.kyupid.kshop.order.domain.OrderProduct;
import com.kyupid.kshop.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderProductResponse {
    private Long orderId;
    private Long orderProductId;
    private Long productId;
    private Integer price;
    private Integer totalAmounts;
    private Integer orderQuantity;

    public static OrderProductResponse from(OrderProduct op) {
        return OrderProductResponse.builder()
                .orderId(op.getOrder().getOrderId())
                .orderProductId(op.getId())
                .productId(op.getProductId())
                .price(op.getPrice())
                .totalAmounts(op.getTotalAmounts())
                .orderQuantity(op.getOrderQuantity())
                .build();
    }
}
