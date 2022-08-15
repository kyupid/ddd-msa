package com.kyupid.kshop.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrdererMemberIdAndOrderId(Long ordererMemberId, Long orderId);

    List<Order> findAllByOrdererMemberId(Long ordererMemberId);
}

