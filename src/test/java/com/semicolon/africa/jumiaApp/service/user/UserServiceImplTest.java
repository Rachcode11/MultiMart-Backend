package com.semicolon.africa.jumiaApp.service.user;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.dtos.request.RegistrationRequest;
import com.semicolon.africa.jumiaApp.registration.RegistrationService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService service;

    private  RegistrationRequest request;



    @BeforeEach
    void setUp(){
        request.setFirstName("Racheal");
        request.setLastName("Joseph");
        request.setUserName("CodeDiva");
        request.setEmailAddress("holuwanifemijoke@gmail.com");
        request.setPassword("Racheal1@");




    }

    @Test
    void createAccount() throws MessagingException {
        assertEquals(service.register(request), "successful");
    }

    @Test
    void login() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void updateUserInfo() {
    }
}