package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "ECOMMERCE", name = "ATTRIBUTE_VALUE")
@Getter
@Setter
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID", nullable = false)
    private Attribute attribute;

    @Column(name = "ATTRIBUTE_VALUE", nullable = false, length = 100)
    private String attributeValue;
}
