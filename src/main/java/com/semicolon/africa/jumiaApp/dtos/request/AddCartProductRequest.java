package com.semicolon.africa.jumiaApp.dtos.request;

import com.semicolon.africa.jumiaApp.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class AddCartProductRequest {

    private String name;
    private Integer quantity;

}
