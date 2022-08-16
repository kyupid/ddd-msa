package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.infra.OrderProductInternalReqRes;

public interface ProductRepository {
    OrderProductInternalReqRes getProductPrice(OrderProductInternalReqRes request);
}
