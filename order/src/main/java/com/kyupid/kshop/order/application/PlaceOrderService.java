package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.application.exception.ValidationErrorException;
import com.kyupid.kshop.order.domain.*;
import com.kyupid.kshop.order.infra.*;
import com.kyupid.kshop.order.presentation.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PlaceOrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    /**
     * 주문요청에 대한 비즈니스 로직
     *
     * 해당 메소드가 하는 역할은 다음과 같다
     * 1. 요청에 대한 모든 프로퍼티를 validate 한다.
     * 2. productRepository 를 통해 Product의 stock을 감소시킨다.
     *   2-1. 이때, productRepository는 인터페이스이기때문에 특정 구현에 대한 의존성을 가지지않는다
     *   2-2. 지금 같은 경우엔 RestTemplate 을 통해 다른 서비스를 호출하는 형태이다
     * 3. 주문요청을 영속화한다.
     *
     * @param orderRequest 사용자의 주문 데이터
     * @return 영속화된 주문 정보
     */
    public List<OrderProduct> placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        // 1. stock을 감소요청한다. Order -> Product
        OrderProductReqRes op = productRepository.decreaseStock(new OrderProductReqRes(orderRequest.getStockAdjustmentList()));

        // 2. order 생성
        Order order = Order.builder()
                .ordererMemberId(orderRequest.getOrdererMemberId())
                .orderStatus(OrderStatus.PAYMENT_WAITING)
                .deliveryInfo(orderRequest.getDeliveryInfo())
                .build();
        orderRepository.save(order);

        // 3. OrderProduct 생성
        List<StockAdjustment> sdList = op.getStockAdjustmentList();
        List<OrderProduct> opList = new ArrayList<>();
        for (StockAdjustment sd : sdList) {
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(order)
                    .productId(sd.getProductId())
                    .price(sd.getPricePerProduct())
                    .orderQuantity(sd.getQuantity())
                    .build();
            opList.add(orderProduct);
        }
        orderProductRepository.saveAll(opList);

        return opList;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
