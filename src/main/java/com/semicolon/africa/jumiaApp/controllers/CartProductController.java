package com.semicolon.africa.jumiaApp.controllers;

import com.semicolon.africa.jumiaApp.data.model.CartProduct;
import com.semicolon.africa.jumiaApp.data.model.Product;
import com.semicolon.africa.jumiaApp.dtos.request.AddCartProductRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateCartProduct;
import com.semicolon.africa.jumiaApp.service.cart.CartProductService;
import com.semicolon.africa.jumiaApp.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/cartProduct")
@CrossOrigin("http://localhost:3000")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @PutMapping("createcartProduct")
    public ResponseEntity<?>createCartProduct(@RequestBody AddCartProductRequest addCartProductRequest, HttpServletRequest httpServletRequest){
        CartProduct addCart = cartProductService.createCartProduct(addCartProductRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(addCart)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("updatecartproduct")
    public ResponseEntity<?>updateCartProduct(@RequestBody UpdateCartProduct updateCartProduct, HttpServletRequest httpServletRequest){
        CartProduct updateProduct = cartProductService.updateCartProduct(updateCartProduct);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(updateCartProduct)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("deletecartProduct/{id}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable("id")Long id, HttpServletRequest httpServletRequest){
        String deleteCartProduct = cartProductService.deleteCartProduct();
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(deleteCartProduct)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
