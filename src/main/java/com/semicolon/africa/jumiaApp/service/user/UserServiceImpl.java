package com.semicolon.africa.jumiaApp.service.user;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.data.model.VerificationOTP;
import com.semicolon.africa.jumiaApp.data.repository.UserRepository;
import com.semicolon.africa.jumiaApp.dtos.request.ChangePasswordRequest;
import com.semicolon.africa.jumiaApp.dtos.request.LoginRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateUserInfoRequest;
import com.semicolon.africa.jumiaApp.exceptions.GenericHandlerException;
import com.semicolon.africa.jumiaApp.utils.OTPGenerator;
import com.semicolon.africa.jumiaApp.verification.VerificationOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationOtpService verificationOtpService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public String createAccount(User user) {
        saveUser(user);
        String Otp = OTPGenerator.generateOtp().toString();
        VerificationOTP verificationOTP = new VerificationOTP(
                Otp,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                user
        );
        verificationOtpService.saveVerificationOTP(verificationOTP);
        return Otp;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public String login(LoginRequest loginRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(loginRequest
                .getEmailAddress()).orElseThrow(()-> new GenericHandlerException("user not found"));
        if (foundUser.getIsDisabled())throw new GenericHandlerException("verify user");
        if (!passwordEncoder.matches(loginRequest.getPassword(),
                foundUser.getPassword()))throw new GenericHandlerException("incorrect password");
        return "login successfully";
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(changePasswordRequest.getEmailAddress()).get();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), (foundUser.getPassword())))throw new GenericHandlerException("incorrect password");
        if (!Objects.equals(changePasswordRequest.getNewPassword(), changePasswordRequest.getConfirmPassword()))throw new GenericHandlerException("Password not match");
        foundUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        return "Password successfully changed";
    }

    @Override
    public Optional<User> findUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public Optional<User> deleteByEmailAddress(String emailAddress) {
        return userRepository.deleteUserByEmailAddress(emailAddress);
    }

    @Override
    public Optional<User> updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        Optional<User> foundUser = findUserByEmailAddress(updateUserInfoRequest.getEmailAddress());
        if (updateUserInfoRequest.getFirstName() != null)foundUser.ifPresent(user -> user.setFirstName(updateUserInfoRequest.getFirstName()));
        if (updateUserInfoRequest.getLastName() != null)foundUser.ifPresent(user -> user.setLastName(updateUserInfoRequest.getLastName()));
        if (updateUserInfoRequest.getUserName() != null)foundUser.ifPresent(user -> user.setUserName(updateUserInfoRequest.getUserName()));
        if (updateUserInfoRequest.getEmailAddress() != null)foundUser.ifPresent(user -> user.setEmailAddress(updateUserInfoRequest.getEmailAddress()));
        userRepository.save(foundUser.get());
        return foundUser;
    }

    @Override
    public void enableUser(String emailAddress) {
        Optional<User> foundUser = findUserByEmailAddress(emailAddress);
        foundUser.isPresent();
        userRepository.save(foundUser.get());
        userRepository.enableUser(emailAddress);
    }
}
