package com.kyupid.kshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JwtSessionController {

    private final JwtAuthService service;

    @PostMapping("/session")
    public LoginResponseData login(@RequestBody @Valid LoginRequestData requestData) {
        return service.login(requestData);
    }
}
