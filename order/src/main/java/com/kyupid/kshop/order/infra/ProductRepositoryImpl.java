package com.kyupid.kshop.order.infra;

import com.kyupid.kshop.order.domain.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static final String REQUEST_URL = "http://localhost:8080";

    /**
     * Stock 을 Reserve 한다.
     *
     * 이유:
     * Order - Prdocut 도메인만 있다면 stock 바로 차감하고 order 생성하면 끝이기 때문에
     * 동시성 문제만 빼면 문제가 없을 수도 있다고 생각
     *
     * 하지만, Payment - Delivery 등의 도메인이 나타난다면?
     * 분산 트랜잭션 -> 어떻게 atomic 하게 관리할 것이냐?
     * stock 바로 차감하고, Payment 또는 Delivery 에서 API 에러가 난다면
     * stock 을 다시 더해줘야한다. 그 중간에 실제 재고와 맞지 않는 상황이 발생한다.
     *
     * 예약 (Reserve)를 활용해보면?
     *
     * @return price, reservedProductIds
     */
    @Override
    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {
        final String URI = "/api/products/reserve/stock";
        return RestClient.postExchange(request, REQUEST_URL + URI, new ParameterizedTypeReference<OrderProductInternalReqRes>() {});
    }

    /**
     * 예약된 stock 을 confirm 해준다.
     *
     * confirm 해주면서 실제 재고도 차감해준다.
     *
     * @param confirmStockRequest
     */
    @Override
    public void confirmStock(ConfirmStockRequest confirmStockRequest) {
        final String URI = "/api/products/confirm/stock";
        RestClient.putExchange(confirmStockRequest, REQUEST_URL + URI, new ParameterizedTypeReference<Void>() {});
    }
}
