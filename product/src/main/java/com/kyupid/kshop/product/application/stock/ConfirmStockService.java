package com.kyupid.kshop.product.application.stock;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.infra.ProductRepository;
import com.kyupid.kshop.product.infra.ReservedStockRepository;
import com.kyupid.kshop.product.presentation.dto.ConfirmStockRequest;
import com.kyupid.kshop.product.presentation.dto.OrderProductInternalReqRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmStockService {

    private final ReservedStockRepository repository;

    @Transactional
    public void confirmStock(ConfirmStockRequest request) {
        List<ReservedStock> reservedStockList = repository.findAllById(request.getReservedStockIdList());
        for (ReservedStock rs : reservedStockList) {
            rs.updateStatusConfirmed();
        }
    }
}
