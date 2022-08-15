package com.kyupid.kshop.order.presentation.dto;

import lombok.Getter;

@Getter
public class ExceptionMessageResponse {

    private final String message;

    public ExceptionMessageResponse(String message) {
        this.message = message;
    }
}
