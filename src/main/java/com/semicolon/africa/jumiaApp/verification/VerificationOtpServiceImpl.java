package com.semicolon.africa.jumiaApp.verification;

import com.semicolon.africa.jumiaApp.data.model.VerificationOTP;
import com.semicolon.africa.jumiaApp.data.repository.VerificationOtpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service

public class VerificationOtpServiceImpl implements VerificationOtpService {

    @Autowired
    private VerificationOtpRepo verificationOtpRepo;
    @Override
    public void saveVerificationOTP(VerificationOTP verificationOTP) {
        verificationOtpRepo.save(verificationOTP);

    }

    @Override
    public Optional<VerificationOTP> findByOTP(String otp) {
        return verificationOtpRepo.findVerificationOTPByOneTimePassword(otp);
    }

    @Override
    public void setVerifiedAt(String otp) {
        verificationOtpRepo.setVerifiedAt(LocalDateTime.now(), otp);

    }

    @Override
    public void deleteOtp() {
        verificationOtpRepo.deleteVerificationOTPByExpiredAtBefore(LocalDateTime.now());

    }
}
