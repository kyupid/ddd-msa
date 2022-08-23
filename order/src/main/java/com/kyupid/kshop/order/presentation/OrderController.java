package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.OrderService;
import com.kyupid.kshop.order.application.PlaceOrderService;
import com.kyupid.kshop.order.auth.JwtAuth;
import com.kyupid.kshop.order.domain.Order;
import com.kyupid.kshop.order.presentation.dto.OrderProductResponse;
import com.kyupid.kshop.order.presentation.dto.OrderRequest;
import com.kyupid.kshop.order.presentation.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: HttpServletRequest -> 어노테이션으로 memberId 매핑하기
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final PlaceOrderService placeOrderService;
    private final Long TEMP_MEMBER_ID = 1L;

    private final JwtAuth jwtAuth;

    @GetMapping
    public List<OrderResponse> getOrders() {
        Long memberId = jwtAuth.getMemberId();
        List<Order> orders = orderService.getOrders(memberId);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderProductResponse> opr = order.getOrderProductList().stream()
                    .map(OrderProductResponse::from)
                    .collect(Collectors.toList());
            orderResponseList.add(new OrderResponse(opr, memberId, order.getDeliveryInfo()));
        }
        return orderResponseList;
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(jwtAuth.getMemberId(), orderId);
        List<OrderProductResponse> oprList = order.getOrderProductList().stream()
                .map(OrderProductResponse::from)
                .collect(Collectors.toList());
        return new OrderResponse(oprList, order.getOrdererMemberId(), order.getDeliveryInfo());
    }

    @PostMapping
    public OrderResponse orderProduct(@RequestBody OrderRequest orderRequest) {
        orderRequest.setOrdererId(jwtAuth.getMemberId());
        List<OrderProductResponse> oprList = placeOrderService.placeOrder(orderRequest).stream()
                .map(OrderProductResponse::from)
                .collect(Collectors.toList());
        return new OrderResponse(oprList, orderRequest.getOrdererMemberId(), orderRequest.getDeliveryInfo());
    }

    @PutMapping("/{orderId}")
    public String changeOrder(@RequestBody OrderRequest orderRequest,
                              @PathVariable Long orderId) {
        orderRequest.setOrdererId(jwtAuth.getMemberId());
        orderService.changeOrder(orderRequest, orderId);
        return null;
    }

    @DeleteMapping("/{orderId}")
    public String cancelOrder(@RequestBody OrderRequest orderRequest,
                              @PathVariable Long orderId) {
        orderRequest.setOrdererId(TEMP_MEMBER_ID);
        // TODO: @RequestBody에  OrderStatus를 받아야하는가?
        // 운영자면 받아야하고 일반유저면 안받아야하고

        orderService.changeOrder(orderRequest, orderId);
        // 상품 수량 변경 -> product api
        return null;
    }

}
