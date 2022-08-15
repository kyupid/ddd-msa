package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderRepository;
import com.kyupid.kshop.order.domain.ProductRepository;
import com.kyupid.kshop.order.presentation.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(OrderRequest orderRequest) {
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        // TODO: price 가져온걸로 order만들기
        productRepository.getProductPrice(orderRequest.getOrderProductList());


        // TODO 세이브 완료되면 commit API back 하기
//        orderRepository.save(order);
//        return order;
        return null;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }
}
