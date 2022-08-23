package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.stock.StockService;
import com.kyupid.kshop.product.presentation.dto.OrderProductReqRes;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final StockService stockService;

    @PostMapping("/decrease/stock")
    public OrderProductReqRes decreaseStock(@RequestBody OrderProductReqRes request) {
        return stockService.decreaseStock(request);
    }

    @PostMapping("/increase/stock")
    public void increaseStock(@RequestBody List<StockAdjustment> saList) {
        stockService.increaseStock(saList);
    }
}
