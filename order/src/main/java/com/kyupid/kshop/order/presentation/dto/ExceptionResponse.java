package com.kyupid.kshop.order.presentation.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse<T> {

    private final T data;
    private final String message;

    public ExceptionResponse(String message) {
        this.data = null;
        this.message = message;
    }

    public ExceptionResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }
}
