package com.kyupid.kshop.order.application;

import com.kyupid.kshop.order.domain.*;
import com.kyupid.kshop.order.infra.StockAdjustment;
import com.kyupid.kshop.order.presentation.ChangeDeliveryRequest;
import com.kyupid.kshop.order.presentation.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
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

    @Transactional
    public Order changeDeliveryInfo(ChangeDeliveryRequest request, Long orderId) {
        List<ValidationError> errors = validateOrderRequest(request);
        if (!errors.isEmpty()) throw new ValidationErrorException(errors);

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException(orderId.toString()));
        order.changeDeliveryInfo(request.getDeliveryInfo());
        return order;
    }

    private List<ValidationError> validateOrderRequest(ChangeDeliveryRequest request) {
        return new OrderRequestValidator().validate(request);
    }

    @Transactional
    public void cancelOrder(Long orderId, Long memberId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        if (!hasCancellationPermission(orderId, memberId)) {
            throw new NoCancellationPermissionException();
        }
        increaseStock(order);
        order.cancel();
    }

    private void increaseStock(Order order) {
        List<OrderProduct> opList = order.getOrderProductList();
        List<StockAdjustment> saList = opList.stream()
                .map(StockAdjustment::from)
                .collect(Collectors.toList());
        productRepository.increaseStock(saList);
    }

    public boolean hasCancellationPermission(Long orderId, Long memberId) {
        return isCancellerOrderer(orderId, memberId) || isCurrentUserAdminRole();
    }

    private boolean isCancellerOrderer(Long orderId, Long memberId) {
        return orderId.equals(memberId);
    }

    private boolean isCurrentUserAdminRole() {
        /**
         * 현재 유저가 어드민인지 체크하는 로직을 삽입
         */
        return true;
    }
}
