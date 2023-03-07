package com.semicolon.africa.jumiaApp.verification;

import com.semicolon.africa.jumiaApp.data.model.VerificationOTP;

import java.util.Optional;

public interface VerificationOtpService {

    void saveVerificationOTP(VerificationOTP verificationOTP);
    Optional<VerificationOTP> findByOTP(String otp);

    void setVerifiedAt(String otp);

    void deleteOtp();
}



