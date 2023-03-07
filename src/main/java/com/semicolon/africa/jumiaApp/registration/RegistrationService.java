package com.semicolon.africa.jumiaApp.registration;

import com.semicolon.africa.jumiaApp.dtos.request.RegistrationRequest;
import com.semicolon.africa.jumiaApp.dtos.request.SendOTPRequest;
import com.semicolon.africa.jumiaApp.dtos.request.VerifyOtpRequest;
import jakarta.mail.MessagingException;

public interface RegistrationService {

    String register(RegistrationRequest registrationRequest) throws MessagingException;

    String verifyOtp(VerifyOtpRequest verifyOtpRequest);

    String reSendVerificationOtp(SendOTPRequest sendOTPRequest);
}
