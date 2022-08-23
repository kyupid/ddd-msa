package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final RestClient restClient;

    /**
     * 무조건 감소시켜야하는 Order place 같은 경우 아래 이용
     */
    @Override
    public OrderProductReqRes decreaseStock(OrderProductReqRes request) {
        final String URI = "/api/products/decrease/stock";
        return restClient.patchExchange(request, URI, new ParameterizedTypeReference<OrderProductReqRes>() {});
    }

    @Override
    public void increaseStock(List<StockAdjustment> request) {
        final String URI = "/api/products/increase/stock";
        restClient.patchExchange(request, URI, new ParameterizedTypeReference<Void>() {});
    }
}
