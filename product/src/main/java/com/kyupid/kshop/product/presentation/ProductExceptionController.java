package com.kyupid.kshop.product.presentation;

import com.kyupid.kshop.product.application.ProductId;
import com.kyupid.kshop.product.application.ProductNotFoundException;
import com.kyupid.kshop.product.application.DuplicatedNameException;
import com.kyupid.kshop.product.presentation.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ProductExceptionController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionResponse<ProductId> handleProductNotFound(ProductNotFoundException e) {
        return new ExceptionResponse<>(e.getProductId(), "해당되는 상품을 찾지 못했습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedNameException.class)
    public ExceptionResponse<String> handleDuplicatedName() {
        return new ExceptionResponse<>("상품 이름이 중복입니다.");
    }
}
