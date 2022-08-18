package com.kyupid.kshop.order.application;

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

    public List<OrderProduct> placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        // 1. stock을 reserve 한다.
        OrderProductInternalReqRes op = productRepository.reserveStock(new OrderProductInternalReqRes(orderRequest.getStockAdjustmentList()));

        // 2. order 생성
        Order order = Order.builder()
                .ordererMemberId(orderRequest.getOrdererMemberId())
                .orderStatus(OrderStatus.PAYMENT_WAITING) // 결제 도메인이없으므로 모두 PAYMENT_WAITING
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

        // 4. reserve 된 stock 을 confrim 한다
        productRepository.confirmStock(new ConfirmStockRequest(op.getReservedStockIdList()));
        return opList;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
