package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.domain.Status;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.infra.ReservedStockRepository;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductRepository productRepository;
    private final ReservedStockRepository reservedStockRepository;

    @Transactional
    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {

        // 1. stock 예약
        List<StockAdjustment> saList = request.getStockAdjustmentList();

        // 1-1. stock 있는지 없는지 체크
        List<Long> stockValidation = new ArrayList<>();
        for (StockAdjustment sa : saList) {
            // 1-1-1. ReservedStock.status == RESERVED, ReservedStock.sa.productId == ReservedStock.productId 의 quantity
            Integer stock = productRepository.findStockById(sa.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(sa.getProductId()));

            log.info("stock: {}", stock);

            Integer reservedQuantity = reservedStockRepository.findReservedQuantityByProductIdAndStatus(sa.getProductId(), Status.RESERVED)
                    .orElse(0);
            log.info("reservedQuantity: {}", reservedQuantity);
            int availableStock = stock + reservedQuantity;
            log.info("availabeStock: {}", stock);

            if (sa.getQuantity() > availableStock) {
                stockValidation.add(sa.getProductId());
            }
        }
        if (stockValidation.size() > 0) {
            throw new NotEnoughStockException(stockValidation);
        }

        List<ReservedStock> rsList = saList.stream()
                .map(ReservedStock::new) // 지난주차 배운거 써먹음
                .collect(Collectors.toList());
        log.info(">>> ReservedStockList: {}", rsList);
        reservedStockRepository.saveAll(rsList);

        // 2. saList prices
        for (StockAdjustment sa : saList) {
            Integer productPrice = productRepository.findPriceByProductIdAndOrderQuantity(sa.getProductId(), sa.getQuantity())
                    .orElseThrow(() -> new ProductNotFoundException(sa.getProductId())); // TODO: [order]GeneralNotFoundException
            sa.setPricePerProduct(productPrice);
            System.out.println("stockAdjustment :" + sa);
        }

        log.info(">>> StockAdjustmentList: {}", saList);

        System.out.println("reserveStock 끝!!!!!");
        return new OrderProductInternalReqRes(saList);
    }
}
