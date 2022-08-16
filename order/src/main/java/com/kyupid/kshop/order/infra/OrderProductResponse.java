package com.kyupid.kshop.order.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductResponse {
    private List<OrderProductWithPrice> list;
}
