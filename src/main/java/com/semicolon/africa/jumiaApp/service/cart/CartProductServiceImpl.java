package com.semicolon.africa.jumiaApp.service.cart;

import com.semicolon.africa.jumiaApp.data.model.CartProduct;
import com.semicolon.africa.jumiaApp.data.model.Product;
import com.semicolon.africa.jumiaApp.data.repository.CartProductRepository;
import com.semicolon.africa.jumiaApp.data.repository.ProductRepository;
import com.semicolon.africa.jumiaApp.dtos.request.AddCartProductRequest;
import com.semicolon.africa.jumiaApp.dtos.request.UpdateCartProduct;
import com.semicolon.africa.jumiaApp.exceptions.GenericHandlerException;
import com.semicolon.africa.jumiaApp.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartProductServiceImpl implements CartProductService{

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductService productService;

    @Override
    public CartProduct createCartProduct(AddCartProductRequest addCartProductRequest) {
      Product foundProduct = productService.findProductByName(addCartProductRequest.getName()).orElseThrow(()-> new GenericHandlerException("product not found"));
      CartProduct cartProduct = new CartProduct();
      cartProduct.setName(foundProduct.getName());
      cartProduct.setTotalPrice(foundProduct.getUnitPrice());
      cartProduct.setQuantity(foundProduct.getQuantity());
      cartProduct.setTotalPrice(foundProduct.getUnitPrice().multiply(new BigDecimal(addCartProductRequest.getQuantity())));
      return cartProductRepository.save(cartProduct);

      
    }

    @Override
    public void deleteCartProductById(Long id) {
        cartProductRepository.deleteCartProductById(id);

    }

    @Override
    public List<CartProduct> getAllCartProduct() {
        return cartProductRepository.findAll();
    }

    @Override
    public String deleteCartProduct() {
         cartProductRepository.deleteAll();
         return "cartProduct deleted";
    }

    @Override
    public CartProduct updateCartProduct(UpdateCartProduct updateCartProduct) {
        CartProduct foundCartProduct = cartProductRepository.findCartProductById(updateCartProduct.getCartProductId());
        foundCartProduct.setQuantity(updateCartProduct.getQuantity());
        foundCartProduct.getTotalPrice().multiply(new BigDecimal(updateCartProduct.getQuantity()));
        return cartProductRepository.save(foundCartProduct);
    }

    @Override
    public void deleteAllCartProduct() {
        cartProductRepository.deleteAll();
    }
}
