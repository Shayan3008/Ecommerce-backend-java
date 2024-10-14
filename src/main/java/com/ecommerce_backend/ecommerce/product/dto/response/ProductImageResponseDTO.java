package com.ecommerce_backend.ecommerce.product.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponseDTO {
    private Integer id;
    private Integer combinationId;
    private String imageUrl;
}
