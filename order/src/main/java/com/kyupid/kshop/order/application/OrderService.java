package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.*;
import com.kyupid.kshop.order.infra.StockAdjustment;
import com.kyupid.kshop.order.presentation.dto.ChangeOrderRequest;
import com.kyupid.kshop.order.presentation.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Order> getOrders(Long ordererMemberId) {
        return orderRepository.findAllByOrdererMemberId(ordererMemberId);
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long memberId, Long orderId) {
        Order order = orderRepository.findByOrdererMemberIdAndOrderId(memberId, orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        return order;
    }

    /**
     * 수량, 배송 둘다 변경한다고 가정 TODO: 나누기
     */
    @Transactional
    public Order changeOrder(ChangeOrderRequest orderRequest, Long orderId) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(orderId.toString()));

        // 1. 배송정보 변경
        order.changeDeliveryInfo(orderRequest);

        // 2. Product stock 변경
        productRepository.changeStock(orderRequest);

        // 3. Procut stock 변경 후, OrderProduct 변경 (주문 수량, 가격)
        List<OrderProduct> opList = order.getOrderProductList();
        List<StockAdjustment> saList = orderRequest.getStockAdjustmentList();
        for (OrderProduct op : opList) {
            for (StockAdjustment sa : saList) {
                if (op.getProductId().equals(sa.getProductId())) {
                    op.updateOrderProductInfo(sa);
                }
            }
        }
        return order;
    }

    private List<ValidationError> validateOrderRequest(ChangeOrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
