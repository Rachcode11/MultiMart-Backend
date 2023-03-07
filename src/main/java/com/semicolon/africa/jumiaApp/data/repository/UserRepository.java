package com.semicolon.africa.jumiaApp.data.repository;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateUserInfoRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAddressIgnoreCase(String emailAddress);
    Optional<User> deleteUserByEmailAddress(String emailAddress);

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.isDisabled=false WHERE user.emailAddress= ?1")
    void enableUser(String emailAddress);

}
