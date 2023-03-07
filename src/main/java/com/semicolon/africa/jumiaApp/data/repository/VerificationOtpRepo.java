package com.semicolon.africa.jumiaApp.data.repository;

import com.semicolon.africa.jumiaApp.data.model.VerificationOTP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VerificationOtpRepo extends JpaRepository<VerificationOTP, Long> {

    void deleteVerificationOTPByExpiredAtBefore(LocalDateTime currentTime);
    Optional<VerificationOTP> findVerificationOTPByOneTimePassword(String oneTimePassword);

    @Transactional
    @Modifying
    @Query("UPDATE VerificationOTP verificationOTP " +
            "SET verificationOTP.verifiedAt = ?1 " +
            "WHERE verificationOTP.oneTimePassword = ?2")
    void setVerifiedAt(LocalDateTime now, String oneTimePassword);
}
