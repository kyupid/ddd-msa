package com.kyupid.kshop.order.application.exception;

import com.kyupid.kshop.order.application.ValidationError;

import java.util.List;

public class ValidationErrorException extends RuntimeException {
    private List<ValidationError> errors;

    public ValidationErrorException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}

