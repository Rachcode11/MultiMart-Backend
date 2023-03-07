package com.semicolon.africa.jumiaApp.dtos.request;

import com.semicolon.africa.jumiaApp.enums.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProduct {
    private String name;
    private Category category;
    private BigDecimal price;
    private Integer quantity;
}
