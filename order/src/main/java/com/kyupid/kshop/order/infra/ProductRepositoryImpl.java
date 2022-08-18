package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.domain.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static final String REQUEST_URL = "http://localhost:8080";

    /**
     * Product 애그리거트로 아래 파라미터를 통해서 수량을 validate하고
     * 가격을 리턴한다
     *
     * @return 해당 quantity만큼의 price를 return한다
     */
    @Override
    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {
        String URI = "/api/products/reserve/stock";
        return RestClient.postExchange(request, REQUEST_URL + URI, new ParameterizedTypeReference<OrderProductInternalReqRes>() {});
    }

    @Override
    public void confirmStock(ConfirmStockRequest confirmStockRequest) {
        String URI = "/api/products/confirm/stock";
        RestClient.putExchange(confirmStockRequest, REQUEST_URL + URI, new ParameterizedTypeReference<Void>() {});
    }
}
