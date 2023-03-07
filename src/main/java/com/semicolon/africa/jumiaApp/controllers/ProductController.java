package com.semicolon.africa.jumiaApp.controllers;

import com.semicolon.africa.jumiaApp.dtos.request.AddProduct;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateProductRequest;
import com.semicolon.africa.jumiaApp.service.productService.ProductService;
import com.semicolon.africa.jumiaApp.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService;

    @PutMapping("addproduct")
    public ResponseEntity<?>add(@RequestBody AddProduct addProduct, HttpServletRequest httpServletRequest){
        String addStock = productService.addProduct(addProduct);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(addStock)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?>update(@RequestBody UpdateProductRequest updateProductRequest, HttpServletRequest httpServletRequest){
        String updateStock = productService.updateProduct(updateProductRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .data(updateStock)
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
