package com.kyupid.kshop.order.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class DeliveryInfo {
    private String receiverName;
    private String phone;
    private String address;
    private String memo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryInfo that = (DeliveryInfo) o;
        return Objects.equals(receiverName, that.receiverName) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(memo, that.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverName, phone, address, memo);
    }
}
