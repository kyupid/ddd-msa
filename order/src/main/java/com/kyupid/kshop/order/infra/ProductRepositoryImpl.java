package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.domain.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String REQUEST_URL = "http://localhost:8080";

    // TODO: 수량없을때 어떻게 리턴하고 받을지 결정

    /**
     * Product 애그리거트로 아래 파라미터를 통해서 수량을 validate하고
     * 가격을 리턴한다
     *
     * @return 해당 quantity만큼의 price를 return한다
     */
    @Override
    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {
        String URI = "/api/products/stock";
        return RestClient.process(request, REQUEST_URL + URI);
    }

//    public boolean commitSubtractQuantity4Product() {
//
//    }
}
