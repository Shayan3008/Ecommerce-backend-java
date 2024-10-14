package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(schema = "ECOMMERCE", name = "PRODUCT_PRICE")
@Getter
@Setter
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "COMBINATION_ID", nullable = false)
    private ProductCombination combination;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;
}
