package com.kyupid.kshop.product.application.stock;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.infra.ReservedStockRepository;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConfirmStockService {

    private final ReservedStockRepository repository;

    @Transactional
    public void confirmStock(List<Long> reservedStockIdList) {
        List<ReservedStock> reservedStockList = repository.findAllById(reservedStockIdList);
        for (ReservedStock rs : reservedStockList) {
            rs.updateStatusConfirmed();
        }
    }
}
