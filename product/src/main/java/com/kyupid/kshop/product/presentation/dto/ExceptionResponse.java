package com.kyupid.kshop.product.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
