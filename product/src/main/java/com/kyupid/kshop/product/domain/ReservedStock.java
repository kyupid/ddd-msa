package com.kyupid.kshop.product.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyupid.kshop.product.presentation.dto.AdjustmentType;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

@ToString
@Slf4j
@Entity
public class ReservedStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 바로 디비에 영속화시키기 위해서
    private Long id;

    private LocalDateTime created;

    private Long productId;

    private Integer reservedQuantity;

    private AdjustmentType adjustmentType;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected ReservedStock() {
    }

    public ReservedStock(StockAdjustment sa) {
        this.productId = sa.getProductId();
        this.reservedQuantity = sa.getQuantity();
        this.adjustmentType = sa.getAdjustmentType();
        this.created = LocalDateTime.now();
        this.status = Status.RESERVED;
    }
}
