package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.infra.ConfirmStockRequest;
import com.kyupid.kshop.order.infra.OrderProductReqRes;
import com.kyupid.kshop.order.presentation.dto.ChangeOrderRequest;

public interface ProductRepository {
    OrderProductReqRes decreaseStock(OrderProductReqRes request);

    void changeStock(ChangeOrderRequest orderRequest);
}
