package com.semicolon.africa.jumiaApp.service.productService;

import com.semicolon.africa.jumiaApp.data.model.Product;
import com.semicolon.africa.jumiaApp.dtos.request.AddProduct;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

   String addProduct(AddProduct addProduct);

   Optional<Product> findProductByName(String name);

   void deleteAllProduct();
   List<Product> getAllProduct();

   String deleteProductByName(String name);
   String updateProduct(UpdateProductRequest updateProductRequest);
}
