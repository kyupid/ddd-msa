package com.kyupid.kshop.order.presentation;

import com.kyupid.kshop.order.application.ValidationError;
import com.kyupid.kshop.order.application.ValidationErrorException;
import com.kyupid.kshop.order.infra.ExternalApiServerException;
import com.kyupid.kshop.order.infra.GeneralNotFoundException;
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
        return new ExceptionMessageResponse("인증된 사용자가 아닙니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationErrorException.class)
    public List<ValidationError> handleValidationError(ValidationErrorException e) {
        return e.getErrors();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExternalApiServerException.class)
    public ExceptionMessageResponse handleExternalApiServerException() {
        return new ExceptionMessageResponse("서버 통신 도중에 문제가 생겼습니다.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GeneralNotFoundException.class)
    public ExceptionMessageResponse handleGeneralNotFoundException() { // TODO: 어떤 품목이없는지 리스트로 보내야함
        return new ExceptionMessageResponse("GeneralNotFoundException");
    }
}
