package com.ecommerce_backend.ecommerce.product.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeValueResponseDTO {
    private Integer id;
    private Integer attributeId;
    private String attributeValue;
}
