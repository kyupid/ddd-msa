package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.domain.OrderProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockAdjustment {
    private Long productId;
    private Integer quantity;
    private Integer pricePerProduct;

    public StockAdjustment(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerProduct = null;
    }

    public static StockAdjustment from(OrderProduct op) {
        return new StockAdjustment(op.getProductId(), op.getOrderQuantity());
    }
}
