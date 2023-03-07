package com.semicolon.africa.jumiaApp.data.model;

import com.semicolon.africa.jumiaApp.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "This field is required")
    @NotNull(message = "This field is required")
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal unitPrice;
    @NotNull(message = "This field is required")
    private Integer quantity;
    private Instant createdTime = Instant.now();
    private Instant updatedTime = Instant.now();
}
