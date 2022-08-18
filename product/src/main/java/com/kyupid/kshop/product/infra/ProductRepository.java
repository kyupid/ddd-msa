package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.Product;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    @Query("select p.price from Product p where p.id = :productId and p.stock >= :orderQuantity")
    Optional<Integer> findPriceByProductIdAndOrderQuantity(@Param("productId") Long productId,
                                                      @Param("orderQuantity") Integer orderQuantity);
}
