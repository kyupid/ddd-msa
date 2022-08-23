package com.kyupid.kshop.order.domain;

import com.kyupid.kshop.order.presentation.dto.ChangeOrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    void changeStock(ChangeOrderRequest orderRequest);
}
