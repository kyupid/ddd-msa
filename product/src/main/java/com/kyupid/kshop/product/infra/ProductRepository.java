package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.presentation.dto.OrderProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * DB, 메시징시스템, 외부시스템과의 연동 등의 실제 구현은 infastructure (이하 infra)에서 수행
 * 따라서 ProductRepository 인터페이스 구현체가 infra 에 가야하지만
 * JpaRepository의 구현체는 IoC컨테이너가 주입해주므로 infra에 아무것도 없는 상황
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    @Query("select new com.kyupid.kshop.product.presentation.dto.OrderProductDto(p.id, p.price)" +
            "from Product p where p.id = :productId and p.stock >= :quantity")
    Optional<OrderProductDto> findOrderProductWithPrice(@Param("productId") Long productId,
                                                        @Param("quantity") Integer quantity);
}
