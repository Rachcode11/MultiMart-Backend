package com.semicolon.africa.jumiaApp.data.repository;

import com.semicolon.africa.jumiaApp.data.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    CartProduct findCartProductById(Long id);

    void deleteCartProductById(Long id);

}
