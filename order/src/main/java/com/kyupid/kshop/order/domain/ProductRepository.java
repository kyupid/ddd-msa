package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.application.OrderProductRequest;
import com.kyupid.kshop.order.infra.OrderProductResponse;

import java.util.List;

public interface ProductRepository {
    OrderProductResponse getProductPrice(List<OrderProductRequest> request);
}
