package com.kyupid.kshop.order.presentation.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChangeOrderRequest extends OrderRequest {

    @NotBlank
    private AdjustmentType adjustmentType;
}
