package com.kyupid.kshop.order.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Version
    private Integer version;

    @Builder
    public Order(OrderStatus orderStatus, Long ordererMemberId, DeliveryInfo deliveryInfo) {
        this.orderStatus = orderStatus;
        this.ordererMemberId = ordererMemberId;
        this.deliveryInfo = deliveryInfo;
        this.orderDate = LocalDateTime.now();
    }

    public void changeDeliveryInfo(DeliveryInfo deliveryInfo) {
        verifyNotYetShipped();
        setDeliveryInfo(deliveryInfo);
    }

    private void verifyNotAlreadyCanceled() {
        if(orderStatus == OrderStatus.CANCELED){
            throw new AlreadyCanceledException();
        }
    }

    private void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    private void verifyNotYetShipped() {
        if (!isNotYetShipped()) {
            throw new AlreadyShippedException();
        }
    }

    public boolean isNotYetShipped() {
        return orderStatus == OrderStatus.PAYMENT_WAITING || orderStatus == OrderStatus.PREPARING;
    }

    public void cancel() {
        verifyNotAlreadyCanceled();
        verifyNotYetShipped();
        this.orderStatus = OrderStatus.CANCELED;
    }
}
