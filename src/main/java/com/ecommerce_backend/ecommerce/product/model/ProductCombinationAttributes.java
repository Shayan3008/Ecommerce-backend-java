package com.ecommerce_backend.ecommerce.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(schema = "ECOMMERCE", name = "PRODUCT_COMBINATION_ATTRIBUTES")
@Getter
@Setter
public class ProductCombinationAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "COMBINATION_ID")
    private ProductCombination combination;

    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "VALUE_ID")
    private AttributeValue value;
}


