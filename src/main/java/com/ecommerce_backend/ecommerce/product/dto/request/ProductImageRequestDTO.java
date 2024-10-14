package com.ecommerce_backend.ecommerce.product.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageRequestDTO {
    private Integer combinationId;
    private String imageUrl;
}
