package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservedStockRepository extends JpaRepository<ReservedStock, Long> {

    @Query("select rs.reservedQuantity from ReservedStock rs where rs.productId = :productId and rs.status = :status")
    Optional<Integer> findReservedQuantityByProductIdAndStatus(Long productId,
                                                               Status status);
}
