package com.kyupid.kshop.product.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            log.error(">>> 정상적으로 객체를 serialize 하지 못했습니다.");
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
