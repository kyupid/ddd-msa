package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.stock.StockService;
import com.kyupid.kshop.product.presentation.dto.OrderProductReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final StockService stockService;

    @PatchMapping("/stock")
    public OrderProductReqRes decreaseStock(@RequestBody OrderProductReqRes request) {
        return stockService.decreaseStock(request);
    }
}
