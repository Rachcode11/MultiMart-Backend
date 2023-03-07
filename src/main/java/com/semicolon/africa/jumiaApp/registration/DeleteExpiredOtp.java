package com.semicolon.africa.jumiaApp.registration;

import com.semicolon.africa.jumiaApp.verification.VerificationOtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DeleteExpiredOtp {

    @Autowired
    private VerificationOtpService verificationOtpService;

    @Scheduled(cron = " 0 3 12 * * *")
    public void deleteExpiredOtp(){
        verificationOtpService.deleteOtp();
        System.out.println("deleted");
    }


}
