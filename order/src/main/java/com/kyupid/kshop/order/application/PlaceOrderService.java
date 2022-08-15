package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderRepository;
import com.kyupid.kshop.order.presentation.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

    private final OrderRepository orderRepository;

    public Order placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        Order order = orderRequest.toEntity();
        orderRepository.save(order);
        // TODO: 상품 수량 변경 -> product api
        return order;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
