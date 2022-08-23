package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.infra.ConfirmStockRequest;
import com.kyupid.kshop.order.infra.OrderProductReqRes;

public interface ProductRepository {
    OrderProductReqRes decreaseStock(OrderProductReqRes request);
}
