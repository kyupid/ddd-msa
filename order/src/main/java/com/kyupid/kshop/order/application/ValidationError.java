package com.kyupid.kshop.order.application;

import lombok.Getter;

@Getter
public class ValidationError {
    private String name;
    private String code;

    public ValidationError(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ValidationError of(String code) {
        return new ValidationError(null, code);
    }

    public static ValidationError of(String name, String code) {
        return new ValidationError(name, code);
    }
}
