package com.kyupid.kshop.order.auth;

import lombok.Getter;

@Getter
public class JwtException extends RuntimeException {

    private String message;
    public JwtException(String message) {
        this.message = message;
    }
}
