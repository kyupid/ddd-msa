package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.application.OrderProductRequest;
import com.kyupid.kshop.order.domain.ProductRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String REQUEST_URL = "http://localhost:8080";

    // TODO: 수량없을때 어떻게 리턴하고 받을지 결정
    /**
     * Product 애그리거트로 아래 파라미터를 통해서 수량을 validate하고
     * 가격을 리턴한다
     * @return 해당 quantity만큼의 price를 return한다
     */
    @Override
    public OrderProductResponse getProductPrice(List<OrderProductRequest> request) {
        JSONObject process = RestClient.process(request, REQUEST_URL);
        return (OrderProductResponse) process.get("data");
    }

//    public boolean commitSubtractQuantity4Product() {
//
//    }
}
