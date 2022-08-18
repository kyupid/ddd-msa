package com.kyupid.kshop.product.domain;

import com.kyupid.kshop.product.presentation.dto.AdjustmentType;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@ToString
@Slf4j
@Getter
@Entity
public class ReservedStock {

    private static final long TIMEOUT = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 바로 디비에 영속화시키기 위해서
    private Long id;

    private LocalDateTime created;
    private LocalDateTime expires;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer reservedQuantity;

    private AdjustmentType adjustmentType;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected ReservedStock() {
    }

    public ReservedStock(Product product, StockAdjustment sa, AdjustmentType adjustmentType) {
        this.product = product;
        this.reservedQuantity = sa.getQuantity();
        this.adjustmentType = adjustmentType;
        this.created = LocalDateTime.now();
        this.expires = created.plus(TIMEOUT, ChronoUnit.SECONDS);
        this.status = Status.RESERVED;
    }

    public void updateStatusConfirmed() {
        getProduct().commitStockSubtraction(this.reservedQuantity);
        this.status = Status.CONFIRMED;
    }

    public Boolean isStockAvailable(Integer requestQuantity) {
        int availableStock = getProduct().getStock() - this.reservedQuantity;
        return requestQuantity > availableStock;
    }
}
