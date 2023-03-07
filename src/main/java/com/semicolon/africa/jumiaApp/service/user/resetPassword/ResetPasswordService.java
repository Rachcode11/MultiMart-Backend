package com.semicolon.africa.jumiaApp.service.user.resetPassword;

import com.semicolon.africa.jumiaApp.dtos.request.ResetPasswordRequest;
import com.semicolon.africa.jumiaApp.dtos.request.SendOTPRequest;
import com.semicolon.africa.jumiaApp.dtos.request.VerifyOtpRequest;

public interface ResetPasswordService {

    String emailOtp(SendOTPRequest sendOTPRequest);

    String verifyOtp(VerifyOtpRequest verifyOtpRequest);

    String resetPassword(ResetPasswordRequest resetPasswordRequest);
}
