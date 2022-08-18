package com.kyupid.kshop.product.infra;

import com.kyupid.kshop.product.domain.ReservedStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedStockRepository extends JpaRepository<ReservedStock, Long> {
}
