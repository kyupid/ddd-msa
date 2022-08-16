package com.kyupid.kshop.order.infra;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderProductWithPrice {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
}
