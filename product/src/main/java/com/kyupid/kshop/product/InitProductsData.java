package com.kyupid.kshop.product;

import com.kyupid.kshop.product.application.ProductService;
import com.kyupid.kshop.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 조회 테스트를 위한 임시 데이터 초기화 클래스
 */
@Component
public class InitProductsData {

    @Autowired
    private ProductService productService;

    /**
     * 샘플 상품 데이터
     */
    @PostConstruct
    public void initProducts() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product("Product" + i, 100 * i, 10);
            productService.createProduct(product);
        }
    }
}
