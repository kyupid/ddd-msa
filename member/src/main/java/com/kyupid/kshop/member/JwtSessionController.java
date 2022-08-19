package com.kyupid.kshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JwtSessionController {

    private final JwtAuthService service;

    @PostMapping("/session")
    public LoginResponseData login(@RequestBody @Valid LoginRequestData requestData) {
        return service.login(requestData);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception() {
        return "로그인 실패";
    }
}
