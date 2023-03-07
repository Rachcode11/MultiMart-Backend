package com.semicolon.africa.jumiaApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserInfoRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;

}
