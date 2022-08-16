package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.GetProductPriceService;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import com.kyupid.kshop.product.presentation.dto.OrderProductRequest;
import com.kyupid.kshop.product.presentation.dto.OrderProductResponse;
import com.kyupid.kshop.product.presentation.dto.OrderProductWithPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class OrderProductApiController {

    private final GetProductPriceService getProductPriceService;

    @PostMapping("/price")
    public OrderProductInternalReqRes getProductPrice(@RequestBody OrderProductInternalReqRes request) {
        return getProductPriceService.getPrice(request);
    }
}
