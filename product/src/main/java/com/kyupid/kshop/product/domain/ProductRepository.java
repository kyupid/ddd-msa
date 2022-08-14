package com.kyupid.kshop.product.domain;

import com.kyupid.kshop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DB, 메시징시스템, 외부시스템과의 연동 등의 실제 구현은 infastructure (이하 infra)에서 수행
 * 따라서 ProductRepository 인터페이스 구현체가 infra 에 가야하지만
 * JpaRepository의 구현체는 IoC컨테이너가 주입해주므로 infra에 아무것도 없는 상황
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
