package com.kyupid.kshop.product.presentation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderProductWithPrice {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;
}
