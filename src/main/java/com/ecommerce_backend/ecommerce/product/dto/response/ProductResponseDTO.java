package com.ecommerce_backend.ecommerce.product.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Integer productId;
    private String productName;
    private String description;

}
