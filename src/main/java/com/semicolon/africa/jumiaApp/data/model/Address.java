package com.semicolon.africa.jumiaApp.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "This field is required")
    @NotNull(message = "This field is required")
    private String streetName;
    @NotEmpty(message = "This field is required")
    @NotNull(message = "This field is required")
    private String streetNumber;
    @NotEmpty(message = "This field is required")
    @NotNull(message = "This field is required")
    private String town;
    @NotEmpty(message = "This field is required")
    @NotNull(message = "This field is required")
    private String city;
}
