package com.semicolon.africa.jumiaApp.data.model;

import com.semicolon.africa.jumiaApp.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Setter
@Getter
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;
    @CreationTimestamp
    @Column(name = "created_time")
    private Instant createdTime = Instant.now();
    @UpdateTimestamp
    @Column(name = "updated_time")
    private Instant updatedTime = Instant.now();

}
