package com.kyupid.kshop.order.infra;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderProductResponse {
    private List<OrderProductWithPrice> list;
}
