package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.ValidationError;
import com.kyupid.kshop.order.application.ValidationErrorException;
import com.kyupid.kshop.order.auth.JwtException;
import com.kyupid.kshop.order.infra.ExternalApiServerException;
import com.kyupid.kshop.order.presentation.dto.ExceptionResponse;
import com.kyupid.kshop.order.presentation.exception.NoTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoTokenException.class)
    public ExceptionResponse handleTokenNotFound() {
        return new ExceptionResponse("인증된 사용자가 아닙니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationErrorException.class)
    public List<ValidationError> handleValidationError(ValidationErrorException e) {
        return e.getErrors();
    }

    @ExceptionHandler(ExternalApiServerException.class)
    public ResponseEntity<Object> handleExternalApiServerException(ExternalApiServerException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getResult().toMap());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    public ExceptionResponse handleExternalApiServerException(JwtException e) {
        return new ExceptionResponse(e.getMessage());
    }
}
