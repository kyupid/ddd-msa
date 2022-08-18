package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.NotEnoughStockException;
import com.kyupid.kshop.product.presentation.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class OrderProductExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughStockException.class)
    public ExceptionResponse<List<Long>> handleNotEnoughStockException(NotEnoughStockException e) {
        return new ExceptionResponse(e.getProductIds(),"재고가 충분하지 않습니다."); //TODO: 어떤 product 재고가 충분하지 않은지 response
    }
}
