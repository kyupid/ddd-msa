package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.stock.ConfirmStockService;
import com.kyupid.kshop.product.application.stock.ReserveStockService;
import com.kyupid.kshop.product.presentation.dto.ConfirmStockRequest;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final ReserveStockService reserveStockService;

    @PatchMapping("/stock")
    public OrderProductInternalReqRes adjustStock(@RequestBody OrderProductInternalReqRes request) {
        return reserveStockService.reserveStock(request);
    }
}
