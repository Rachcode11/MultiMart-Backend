package com.semicolon.africa.jumiaApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    private String emailAddress;
    private String password;
    private String confirmPassword;
}
