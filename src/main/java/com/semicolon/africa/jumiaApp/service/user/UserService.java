package com.semicolon.africa.jumiaApp.service.user;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.dtos.request.ChangePasswordRequest;
import com.semicolon.africa.jumiaApp.dtos.request.LoginRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateUserInfoRequest;

import java.util.Optional;

public interface UserService {

    String createAccount(User user);

    void saveUser(User user);

    String login(LoginRequest loginRequest);

    String changePassword(ChangePasswordRequest changePasswordRequest);


    Optional<User> findUserByEmailAddress(String emailAddress);
    Optional<User> deleteByEmailAddress(String emailAddress);

   Optional<User> updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest);


    void enableUser(String emailAddress);
}
