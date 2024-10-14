package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "ECOMMERCE", name = "PRODUCT_COMBINATION")
@Getter
@Setter
public class ProductCombination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;
}
