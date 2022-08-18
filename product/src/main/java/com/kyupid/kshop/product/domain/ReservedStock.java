package com.kyupid.kshop.product.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyupid.kshop.product.presentation.dto.StockAdjustment;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Entity
public class ReservedStock {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime created;

    private String resources;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected ReservedStock() {
    }

    public ReservedStock(StockAdjustment stockAdjustment) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.resources = objectMapper.writeValueAsString(stockAdjustment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.created = LocalDateTime.now();
        this.status = Status.RESERVED;
    }

    public StockAdjustment getResources() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(this.resources, StockAdjustment.class);
        } catch (IOException e) {
            log.error(">>> 정상적으로 resoures를 객체로 매핑하지 못했습니다.");
            return null;
        }
    }
}
