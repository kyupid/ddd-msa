package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.ValidationError;
import com.kyupid.kshop.order.application.ValidationErrorException;
import com.kyupid.kshop.order.presentation.exception.NoTokenException;
import com.kyupid.kshop.order.presentation.dto.ExceptionMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoTokenException.class)
    public ExceptionMessageResponse handleTokenNotFound() {
        return new ExceptionMessageResponse("No authorization");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationErrorException.class)
    public List<ValidationError> handleValidationError(ValidationErrorException e) {
        return e.getErrors();
    }
}
