package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.OrderService;
import com.kyupid.kshop.order.application.PlaceOrderService;
import com.kyupid.kshop.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// TODO: HttpServletRequest -> 어노테이션으로 memberId 매핑하기
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final PlaceOrderService placeOrderService;
    private final Long TEMP_MEMBER_ID = 1L;

    @GetMapping
    public String getOrders(HttpServletRequest request) {
        orderService.getOrders(TEMP_MEMBER_ID);
        return null;
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId) {
        orderService.getOrder(TEMP_MEMBER_ID, orderId);
        return null;
    }

    @PostMapping
    public OrderResponse orderProduct(@RequestBody OrderRequest orderRequest) {
        orderRequest.setOrdererId(TEMP_MEMBER_ID);
        Order order = placeOrderService.placeOrder(orderRequest);
        return OrderResponse.from(order);
    }

    @PutMapping("/{orderId}")
    public String changeOrder(@RequestBody OrderRequest orderRequest,
                              @PathVariable Long orderId) {
        orderRequest.setOrdererId(TEMP_MEMBER_ID);
        // TODO: @RequestBody에  OrderStatus를 받아야하는가?
        // 운영자면 받아야하고 일반유저면 안받아야하고

        orderService.changeOrder(orderRequest, orderId);
        // 상품 수량 변경 -> product api

        return null;
    }

}
