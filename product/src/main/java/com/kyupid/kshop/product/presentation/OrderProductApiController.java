package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.stock.ConfirmStockService;
import com.kyupid.kshop.product.application.stock.ReserveStockService;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final ReserveStockService reserveStockService;
    private final ConfirmStockService confirmStockService;

    @PostMapping("/reserve/stock")
    public OrderProductInternalReqRes reserveStock(@RequestBody OrderProductInternalReqRes request) {
        return reserveStockService.reserveStock(request);
    }

    @PutMapping("/confirm/stock")
    public void reserveStock(@RequestBody List<Long> reservedStockIdList) {
        confirmStockService.confirmStock(reservedStockIdList);
    }
}
