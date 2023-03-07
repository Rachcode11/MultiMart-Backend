package com.semicolon.africa.jumiaApp.data.model;

import com.semicolon.africa.jumiaApp.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String firstName;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String lastName;
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String userName;
    @Email(message = "email must be valid")
    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String emailAddress;
    private String password;
    private Role role;
    private Boolean isDisabled = true;

    public User(String firstName, String lastName, String userName, String emailAddress,  String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userName = userName;
        this.role = role;

    }


}
