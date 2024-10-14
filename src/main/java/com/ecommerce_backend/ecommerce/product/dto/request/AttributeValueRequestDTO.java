package com.ecommerce_backend.ecommerce.product.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeValueRequestDTO {
    private Integer attributeId;
    private String attributeValue;
}
