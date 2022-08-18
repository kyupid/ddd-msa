package com.kyupid.kshop.product.application;

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

import java.util.*;
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

        List<ReservedStock> rsList = new ArrayList<>();
        List<Long> stockValidation = new ArrayList<>();
        List<Long> reservedStockIdList = new ArrayList<>();
        for (StockAdjustment stockAdjustment : saList) {
            Product product = productRepository.findById(stockAdjustment.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(stockAdjustment.getProductId()));
            ReservedStock reservedStock = reservedStockRepository
                    .findByProductIdAndStatus(stockAdjustment.getProductId(), Status.RESERVED);

            stockAdjustment.setPricePerProduct(product.getPrice()); // Order 로 넘겨줌

            storeStockValidation(stockValidation, stockAdjustment, product, reservedStock); // 1. stock 체크
            storeReservedStockToSave(rsList, stockAdjustment, product); // 2. db에 저장할 RS
            storeReservedStockId(reservedStockIdList, reservedStock); // 3. confirm 수행 하기위해 리턴
        }
        stockValidation(stockValidation);

        reservedStockRepository.saveAll(rsList);
        return new OrderProductInternalReqRes(saList, reservedStockIdList);
    }

    private void stockValidation(List<Long> stockValidation) {
        if (stockValidation.size() > 0) {
            throw new NotEnoughStockException(stockValidation);
        }
    }

    private void storeReservedStockToSave(List<ReservedStock> rsList, StockAdjustment sa, Product product) {
        rsList.add(new ReservedStock(product, sa, AdjustmentType.DECREASE));
    }

    private void storeStockValidation(List<Long> stockValidation, StockAdjustment sa, Product product, ReservedStock rs) {
        int availableStock = getAvailableStock(product, rs);

        if (sa.getQuantity() > availableStock) {
            stockValidation.add(sa.getProductId());
        } // 디비 io 줄이고싶다면 바로 throw 해도 될듯
    }

    private int getAvailableStock(Product product, ReservedStock rs) {
        int reservedQuantity = 0;
        if (rs != null) {
            reservedQuantity = rs.getReservedQuantity();
        }
        int availableStock = product.getStock() - reservedQuantity;
        return availableStock;
    }

    private void storeReservedStockId(List<Long> reservedStockIdList, ReservedStock reservedStock) {
        if (reservedStock != null) {
            reservedStockIdList.add(reservedStock.getId());
        }
    }
}
