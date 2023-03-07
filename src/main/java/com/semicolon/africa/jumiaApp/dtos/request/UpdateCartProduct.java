package com.semicolon.africa.jumiaApp.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCartProduct {

    private Long cartProductId;

    private String cartProductName;

    private Integer quantity;
}
