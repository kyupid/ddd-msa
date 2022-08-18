package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.GetProductPriceService;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final GetProductPriceService getProductPriceService;

    @PostMapping("/stock")
    public OrderProductInternalReqRes reserveStock(@RequestBody OrderProductInternalReqRes request) {
        return getProductPriceService.reserveStock(request);
    }
}
