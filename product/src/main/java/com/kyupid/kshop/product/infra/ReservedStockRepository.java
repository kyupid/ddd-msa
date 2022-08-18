package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ReservedStockRepository extends JpaRepository<ReservedStock, Long> {

    @Query("select rs from ReservedStock rs " +
            "where rs.product.id = :productId " +
            "and rs.status = :status " +
            "and rs.expires > :now")
    ReservedStock findReservedStock(Long productId, Status status, LocalDateTime now);
}
