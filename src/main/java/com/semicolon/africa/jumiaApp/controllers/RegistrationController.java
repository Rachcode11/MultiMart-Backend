package com.semicolon.africa.jumiaApp.controllers;

import com.semicolon.africa.jumiaApp.dtos.request.RegistrationRequest;
import com.semicolon.africa.jumiaApp.dtos.request.SendOTPRequest;
import com.semicolon.africa.jumiaApp.dtos.request.VerifyOtpRequest;
import com.semicolon.africa.jumiaApp.registration.RegistrationService;
import com.semicolon.africa.jumiaApp.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping(value = "api/v1/registration")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest httpServletRequest) throws MessagingException {
        {
            String createUser = registrationService.register(registrationRequest);
            ApiResponse apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .data(createUser)
                    .path(httpServletRequest.getRequestURI())
                    .statusCode(HttpStatus.OK.value())
                    .isSuccessful(true)
                     .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "/verifyOtp")
    public ResponseEntity<?>verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest, HttpServletRequest httpServletRequest){
        log.info(verifyOtpRequest.getOneTimePassword());
        String verifyUser = registrationService.verifyOtp(verifyOtpRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(verifyUser)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?>resendOtp(@RequestBody SendOTPRequest sendOTPRequest, HttpServletRequest httpServletRequest){
        String resendOtp = registrationService.reSendVerificationOtp(sendOTPRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(resendOtp)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
