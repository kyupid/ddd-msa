package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.ReservedStock;
import com.kyupid.kshop.product.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservedStockRepository extends JpaRepository<ReservedStock, Long> {

    ReservedStock findByProductIdAndStatus(Long productId, Status status);
}
