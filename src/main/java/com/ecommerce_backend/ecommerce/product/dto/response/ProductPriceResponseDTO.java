package com.ecommerce_backend.ecommerce.product.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPriceResponseDTO {
    private Integer id;
    private Integer combinationId;
    private BigDecimal price;
}
