package com.kyupid.kshop.member;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequestData {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
