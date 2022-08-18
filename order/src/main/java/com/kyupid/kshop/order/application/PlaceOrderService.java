package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.*;
import com.kyupid.kshop.order.infra.OrderProductInternalReqRes;
import com.kyupid.kshop.order.infra.StockAdjustment;
import com.kyupid.kshop.order.presentation.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceOrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        // TODO: price 가져온걸로 order만들기
        OrderProductInternalReqRes op = productRepository.reserveStock(new OrderProductInternalReqRes(orderRequest.getStockAdjustmentList()));

        Order order = Order.builder() // 이걸 orderproduct에 넣는다
                .ordererMemberId(orderRequest.getOrdererMemberId())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.PAYMENT_WAITING) // 결제 도메인이없으므로 모두 PAYMENT_WAITING
                .deliveryInfo(orderRequest.getDeliveryInfo())
                .build();

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

        // TODO 세이브 완료되면 commit API back 하기
        orderRepository.save(order);
        return order;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
