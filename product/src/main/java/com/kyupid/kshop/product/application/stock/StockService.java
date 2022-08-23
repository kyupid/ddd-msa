package com.kyupid.kshop.product.application.stock;

import com.kyupid.kshop.product.application.ProductNotFoundException;
import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.presentation.dto.AdjustmentType;
import com.kyupid.kshop.product.presentation.dto.OrderProductReqRes;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductRepository productRepository;

    @Transactional
    public OrderProductReqRes decreaseStock(OrderProductReqRes request) {
        List<StockAdjustment> saList = request.getStockAdjustmentList();
        List<Long> unavailableStockList = new ArrayList<>();
        for (StockAdjustment stockAdjustment : saList) {
            Product product = productRepository.findById(stockAdjustment.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(stockAdjustment.getProductId()));

            boolean hasAvailableStock = product.hasAvailableStock(stockAdjustment.getQuantity());
            if (hasAvailableStock) {
                product.decreaseStock(stockAdjustment.getQuantity());
            } else {
                unavailableStockList.add(product.getId());
            }

            stockAdjustment.setPricePerProduct(product.getPrice()); // OrderProduct 주문가격정보를 위한 setter
        }
        processStockValidation(unavailableStockList);

        return new OrderProductReqRes(saList);
    }


    private void processStockValidation(List<Long> unavailableStockList) {
        if (unavailableStockList.size() > 0) {
            throw new NotEnoughStockException(unavailableStockList);
        }
    }

    @Transactional
    public void increaseStock(List<StockAdjustment> saList) {
        log.info("saList: {}", saList);
        for (StockAdjustment sa : saList) {
            Product product = productRepository.findById(sa.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(sa.getProductId()));
            product.increaseStock(sa.getQuantity());
        }
    }
}
