package com.kyupid.kshop.product.application;

import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.domain.ProductRepository;
import com.kyupid.kshop.product.presentation.dto.OrderProductRequest;
import com.kyupid.kshop.product.presentation.dto.OrderProductResponse;
import com.kyupid.kshop.product.presentation.dto.OrderProductWithPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductPriceService {

    private final ProductRepository productRepository;

    public List<OrderProductWithPrice> getPrice(List<OrderProductRequest> request) {
        // 1. 수량ㅇ있는지체크
        // 2. 있으면 가져오고
        List<OrderProductWithPrice> list = new ArrayList<>();
        for (OrderProductRequest opr : request) {
            Integer price = productRepository.findPriceByIdAndQuantityLeftGreaterThan(opr.getProductId(), opr.getQuantity());
            if (price != null) {
                list.add(new OrderProductWithPrice(opr.getProductId(), price, opr.getQuantity()));
            }
        }
        return list;
    }
}
