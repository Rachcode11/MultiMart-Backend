package com.semicolon.africa.jumiaApp.service.cart;

import com.semicolon.africa.jumiaApp.data.model.CartProduct;
import com.semicolon.africa.jumiaApp.dtos.request.AddCartProductRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateCartProduct;

import java.util.List;

public interface CartProductService  {

    CartProduct createCartProduct(AddCartProductRequest addCartProductRequest);

    void deleteCartProductById(Long id);

    List<CartProduct> getAllCartProduct();

    String deleteCartProduct();

    CartProduct updateCartProduct(UpdateCartProduct updateCartProduct);


    void deleteAllCartProduct();

}
