package com.kyupid.kshop.order.domain;

public enum OrderStatus {
    PAYMENT_WAITING,
    PREPARING,
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED
}
