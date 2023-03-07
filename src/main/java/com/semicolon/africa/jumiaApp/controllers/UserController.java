package com.semicolon.africa.jumiaApp.controllers;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.dtos.request.ChangePasswordRequest;
import com.semicolon.africa.jumiaApp.dtos.request.LoginRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateUserInfoRequest;
import com.semicolon.africa.jumiaApp.service.user.UserService;
import com.semicolon.africa.jumiaApp.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest){
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(userService.login(loginRequest))
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<?>updateUser(@RequestBody UpdateUserInfoRequest updateUserInfoRequest, HttpServletRequest httpServletRequest){
        Optional<User> updateUser =  userService.updateUserInfo(updateUserInfoRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(updateUser)
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/changepassword")
    public ResponseEntity<?>changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest httpServletRequest){
        String changePin = userService.changePassword(changePasswordRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(changePin)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
