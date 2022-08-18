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
public class StockService {

    private final ProductRepository productRepository;

    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {

        List<StockAdjustment> list = new ArrayList<>();
        for (StockAdjustment sa : request.getStockAdjustmentList()) {
            StockAdjustment stockAdjustment = productRepository.findOrderProductWithPrice(sa.getProductId(), sa.getQuantity())
                    .orElseThrow(() -> new NoSuchElementException(sa.getProductId().toString())); // TODO: order 도메인에서 예외처리
            stockAdjustment.setQuantity(sa.getQuantity());
            System.out.println("stockAdjustment :" + stockAdjustment);
            list.add(stockAdjustment);
        }
        System.out.println("reserveStock 끝!!!!!");
        return new OrderProductInternalReqRes(list);
    }
}
