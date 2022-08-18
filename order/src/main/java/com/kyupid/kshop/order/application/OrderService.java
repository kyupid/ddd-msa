package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.domain.OrderRepository;
import com.kyupid.kshop.order.presentation.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> getOrders(Long ordererMemberId) {
        return orderRepository.findAllByOrdererMemberId(ordererMemberId);
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long memberId, Long orderId) {
        return orderRepository.findByOrdererMemberIdAndOrderId(memberId, orderId)
                .orElseThrow(() -> new NoSuchElementException(orderId.toString()));
    }



    public Order changeOrder(OrderRequest orderRequest, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(orderId.toString()));
        return order;
    }


}