package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.infra.ReservedStockRepository;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        List<StockAdjustment> saList = request.getStockAdjustmentList();
        List<ReservedStock> rsList = saList.stream()
                .map(ReservedStock::new) // 지난주차 배운거 써먹음
                .collect(Collectors.toList());

        log.info(">>> ReservedStockList: {}", rsList);

        reservedStockRepository.saveAll(rsList);

        for (StockAdjustment sa : saList) {
            Integer productPrice = productRepository.findPriceByProductIdAndOrderQuantity(sa.getProductId(), sa.getQuantity())
                    .orElseThrow(() -> new NoSuchElementException(sa.getProductId().toString())); // TODO: [order]GeneralNotFoundException
            sa.setPricePerProduct(productPrice);
            System.out.println("stockAdjustment :" + sa);
        }

        log.info(">>> StockAdjustmentList: {}", saList);

        System.out.println("reserveStock 끝!!!!!");
        return new OrderProductInternalReqRes(saList);
    }
}
