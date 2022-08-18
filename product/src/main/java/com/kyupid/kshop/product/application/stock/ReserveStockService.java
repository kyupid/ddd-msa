package com.kyupid.kshop.product.application.stock;

import com.kyupid.kshop.product.application.ProductNotFoundException;
import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.domain.Status;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.infra.ReservedStockRepository;
import com.kyupid.kshop.product.presentation.dto.AdjustmentType;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
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
public class ReserveStockService {

    private final ProductRepository productRepository;
    private final ReservedStockRepository reservedStockRepository;

    @Transactional
    public OrderProductInternalReqRes reserveStock(OrderProductInternalReqRes request) {
        List<StockAdjustment> saList = request.getStockAdjustmentList();

        List<ReservedStock> rsList = new ArrayList<>();
        List<Long> stockValidation = new ArrayList<>();
        List<Long> reservedStockIdList = new ArrayList<>();
        for (StockAdjustment stockAdjustment : saList) {
            Product product = productRepository.findById(stockAdjustment.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(stockAdjustment.getProductId()));
            ReservedStock reservedStock = reservedStockRepository // TODO: expires 지난건 스케줄러로 상태 TIMEOUT으로 변경
                    .findReservedStock(stockAdjustment.getProductId(), Status.RESERVED, LocalDateTime.now());

            stockAdjustment.setPricePerProduct(product.getPrice()); // Order 로 넘겨줌

            storeStockValidation(stockValidation, stockAdjustment, reservedStock); // 1.stock 체크
            storeReservedStockToSave(rsList, stockAdjustment, product); // 2. db에 저장할 RS
            storeReservedStockId(reservedStockIdList, reservedStock); // 3. confirm 수행 하기위해 리턴
        }
        stockValidation(stockValidation);

        reservedStockRepository.saveAll(rsList);
        return new OrderProductInternalReqRes(saList, reservedStockIdList);
    }

    private void storeStockValidation(List<Long> stockValidation, StockAdjustment stockAdjustment, ReservedStock reservedStock) {
        Boolean stockAvailable = reservedStock.isStockAvailable(stockAdjustment.getQuantity());
        if (!stockAvailable) {
            stockValidation.add(stockAdjustment.getProductId());
        }
    }

    private void stockValidation(List<Long> stockValidation) {
        if (stockValidation.size() > 0) {
            throw new NotEnoughStockException(stockValidation);
        }
    }

    private void storeReservedStockToSave(List<ReservedStock> rsList, StockAdjustment sa, Product product) {
        rsList.add(new ReservedStock(product, sa, AdjustmentType.DECREASE));
    }

    private void storeReservedStockId(List<Long> reservedStockIdList, ReservedStock reservedStock) {
        if (reservedStock != null) {
            reservedStockIdList.add(reservedStock.getId());
        }
    }
}
