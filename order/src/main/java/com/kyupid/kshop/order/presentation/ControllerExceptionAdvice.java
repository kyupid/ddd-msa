package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.presentation.exception.NoTokenException;
import com.kyupid.kshop.order.presentation.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoTokenException.class)
    public ExceptionResponse handleTokenNotFound() {
        return new ExceptionResponse("No authorization");
    }
}
