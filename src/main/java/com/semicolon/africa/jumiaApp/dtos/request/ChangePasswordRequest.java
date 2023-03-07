package com.semicolon.africa.jumiaApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
    private String emailAddress;
}
