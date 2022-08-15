package com.kyupid.kshop.order.presentation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @GetMapping
    public String getOrders() {
        return null;
    }

    @GetMapping("/{orderId}")
    public String getOrder() {
        return null;
    }

    @PostMapping
    public String orderProduct(@RequestBody OrderRequest orderRequest) {
        // 상품 수량 변경 -> product api
        return null;
    }

    @PutMapping("/{orderId}")
    public String changeOrder(@RequestBody OrderRequest orderRequest) {
        // 상품 수량 변경 -> product api

        return null;
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(){
        return null;
    }
}
