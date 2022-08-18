package com.kyupid.kshop.order.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct { //연관관계 주인
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;

    private Integer price;

    private Integer totalAmounts;

    private Integer orderQuantity;

    @Builder
    public OrderProduct(Order order, Long productId, Integer price, Integer orderQuantity) {
        this.order = order;
        this.productId = productId;
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.totalAmounts = calculatePrice(price);
    }

    private Integer calculatePrice(Integer pricePerProduct) {
        return orderQuantity * pricePerProduct;
    }
}
