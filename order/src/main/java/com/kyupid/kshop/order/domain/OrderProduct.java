package com.kyupid.kshop.order.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;

    private Integer orderPrice;

    private Integer orderQuantity;

    public OrderProduct(Order order, Long productId, Integer orderPrice, Integer orderQuantity) {
        this.order = order;
        this.productId = productId;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
    }

    public Integer calculatePrice(Integer pricePerProduct) {
        return orderQuantity * pricePerProduct;
    }
}
