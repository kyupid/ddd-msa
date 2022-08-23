package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.OrderNotFoundException;
import com.kyupid.kshop.order.application.OrderWrongAccessException;
import com.kyupid.kshop.order.application.ValidationError;
import com.kyupid.kshop.order.application.ValidationErrorException;
import com.kyupid.kshop.order.auth.JwtException;
import com.kyupid.kshop.order.domain.AlreadyCanceledException;
import com.kyupid.kshop.order.domain.AlreadyShippedException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderNotFoundException.class)
    public ExceptionResponse handleOrderNotFoundException(OrderNotFoundException e) {
        return new ExceptionResponse("요청하신 주문은 없는 주문번호입니다. 주문번호: " + e.getOrderId());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderWrongAccessException.class)
    public ExceptionResponse handleOrderWrongAccessException() {
        return new ExceptionResponse("비정상적 접근: 본인이 주문한 내역만 확인이 가능합니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyCanceledException.class)
    public ExceptionResponse handleAlreadyCanceledException() {
        return new ExceptionResponse("이미 취소된 주문입니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyShippedException.class)
    public ExceptionResponse handleAlreadyShippedException() {
        return new ExceptionResponse("이미 배송된 주문입니다.");
    }
}
