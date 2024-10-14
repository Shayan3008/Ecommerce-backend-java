package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "ECOMMERCE", name = "ATTRIBUTE")
@Getter
@Setter
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ATTRIBUTE_NAME", nullable = false, length = 100)
    private String attributeName;
}
