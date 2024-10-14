package com.ecommerce_backend.ecommerce.product.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPriceRequestDTO {
    private Integer combinationId;
    private BigDecimal price;
}
