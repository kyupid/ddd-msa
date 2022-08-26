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

    /**
     * order-serice 에 주문이 들어왔을때
     * Product 의 Stock 을 감소시킨다
     *
     * 해당 메소드가 하는 역할은 다음과 같다.
     * 1. stock 이 충분한지 체크한다.
     * 2. stock 이 충분하지 않으면, 사용자에게 충분하지 않은 Product 목록을 내보내기 위한 처리를 한다.
     * 3. 요청한 각 Product 들의 stock 을 요청 quantity 에 맞춰서 감소시킨다
     * 4. order-service 에 내보낼 해당 Product 들의 price 를 set 한다.
     *
     * @param request adjust 할 stockList를 받는다
     * @return
     */
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

    /**
     * order-service 에 cancel 요청이 왔을 때,
     * Product 의 Stock 을 추가해주는 역할을 한다
     *
     * @param saList adjust 할 stockList를 받는다
     */
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
