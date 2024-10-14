package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "ECOMMERCE", name = "PRODUCT_IMAGE")
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "COMBINATION_ID", nullable = false)
    private ProductCombination combination;

    @Column(name = "IMAGE_URL", nullable = false, length = 255)
    private String imageUrl;
}
