package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.Product;
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
        for (StockAdjustment sa : saList) {
            Product product = productRepository.findById(sa.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(sa.getProductId()));
            sa.setPricePerProduct(product.getPrice()); // Order 로 넘겨줌

            validateStock(stockValidation, sa, product);

            rsList.add(new ReservedStock(product, sa));
        }
        if (stockValidation.size() > 0) {
            throw new NotEnoughStockException(stockValidation);
        }

        reservedStockRepository.saveAll(rsList);
        return new OrderProductInternalReqRes(saList);
    }

    private void validateStock(List<Long> stockValidation, StockAdjustment sa, Product product) {
        int availableStock = getAvailableStock(sa, product);

        if (sa.getQuantity() > availableStock) {
            stockValidation.add(sa.getProductId());
        } // 디비 io 줄이고싶다면 바로 throw 해도 될듯
    }

    private int getAvailableStock(StockAdjustment sa, Product product) {
        int reservedQuantity = 0;
        ReservedStock reservedStock = reservedStockRepository.findByProductIdAndStatus(sa.getProductId(), Status.RESERVED);

        if (reservedStock != null) {
            reservedQuantity = reservedStock.getReservedQuantity();
        }
        return product.getStock() - reservedQuantity;
    }
}
