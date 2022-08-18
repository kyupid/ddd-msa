package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetProductPriceService {

    private final ProductRepository productRepository;

    public OrderProductInternalReqRes getPrice(OrderProductInternalReqRes request) {
        // 1. 수량ㅇ있는지체크
        // 2. 있으면 가져오고
        List<StockAdjustment> list = new ArrayList<>();
        for (StockAdjustment sa : request.getStockAdjustmentList()) {
            StockAdjustment stockAdjustment = productRepository.findOrderProductWithPrice(sa.getProductId(), sa.getQuantity())
                    .orElseThrow(() -> new NoSuchElementException(sa.getProductId().toString())); // TODO: order 도메인에서 예외처리
            stockAdjustment.setQuantity(sa.getQuantity());
            System.out.println("stockAdjustment :" + stockAdjustment);
            list.add(stockAdjustment);
        }
        System.out.println("getPrice 끝!!!!!");
        return new OrderProductInternalReqRes(list);
    }
}