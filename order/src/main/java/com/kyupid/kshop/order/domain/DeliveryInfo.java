package com.kyupid.kshop.order.domain;

import javax.persistence.Embeddable;

@Embeddable
public class DeliveryInfo {
    private String receiverName;
    private String phone;
    private String address;
    private String memo;
}
