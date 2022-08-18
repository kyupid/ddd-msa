package com.kyupid.kshop.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private Long ordererMemberId;

    @Embedded
    private DeliveryInfo deliveryInfo;

    private LocalDateTime orderDate;

    @Builder
    public Order(OrderStatus orderStatus, Long ordererMemberId, DeliveryInfo deliveryInfo, LocalDateTime orderDate) {
        this.orderStatus = orderStatus;
        this.ordererMemberId = ordererMemberId;
        this.deliveryInfo = deliveryInfo;
        this.orderDate = orderDate;
    }
}
