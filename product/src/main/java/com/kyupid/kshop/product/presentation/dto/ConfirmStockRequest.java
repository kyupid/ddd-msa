package com.kyupid.kshop.product.presentation.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ConfirmStockRequest {
    private List<Long> reservedStockIdList;
}
