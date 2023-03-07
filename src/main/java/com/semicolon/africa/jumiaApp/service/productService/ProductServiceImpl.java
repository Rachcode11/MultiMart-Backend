package com.semicolon.africa.jumiaApp.service.productService;


import com.semicolon.africa.jumiaApp.data.model.Product;
import com.semicolon.africa.jumiaApp.data.repository.ProductRepository;
import com.semicolon.africa.jumiaApp.dtos.request.AddProduct;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(AddProduct addProduct) {
        Product product = new Product();
        product.setCategory(addProduct.getCategory());
        product.setName(addProduct.getName());
        product.setCreatedTime(Instant.now());
        product.setUnitPrice(addProduct.getPrice());
        product.setQuantity(addProduct.getQuantity());
        product.setUpdatedTime(Instant.now());
        productRepository.save(product);
        return "product is added";

    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public void deleteAllProduct() {
        productRepository.deleteAll();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public String deleteProductByName(String name) {
        Product foundProductName = findProductByName(name).get();
        productRepository.delete(foundProductName);
        return "product deleted successfully";
    }


    @Override
    public String updateProduct(UpdateProductRequest updateProductRequest) {
        Optional <Product> foundProduct = findProductByName(updateProductRequest.getName());
        if (updateProductRequest.getName() != null)foundProduct.get().setUnitPrice(updateProductRequest.getUnitPrice());
        if(updateProductRequest.getCategory() != null)foundProduct.get().setCategory(updateProductRequest.getCategory());
        if(updateProductRequest.getQuantity() != null)foundProduct.get().setQuantity(updateProductRequest.getQuantity());
        productRepository.save(foundProduct.get());
        return "updated successfully";
    }
}
