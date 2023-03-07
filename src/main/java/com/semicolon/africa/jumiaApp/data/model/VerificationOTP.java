package com.semicolon.africa.jumiaApp.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class VerificationOTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oneTimePassword;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime verifiedAt;
    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User user;

    public VerificationOTP(String oneTimePassword, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.oneTimePassword = oneTimePassword;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
