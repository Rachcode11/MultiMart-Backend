package com.semicolon.africa.jumiaApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
    private String password;
}
