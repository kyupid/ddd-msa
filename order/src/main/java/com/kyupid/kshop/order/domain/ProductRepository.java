package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.infra.OrderProductReqRes;
import com.kyupid.kshop.order.infra.StockAdjustment;

import java.util.List;

public interface ProductRepository {
    OrderProductReqRes decreaseStock(OrderProductReqRes request);

    void increaseStock(List<StockAdjustment> saList);
}
