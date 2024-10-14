package com.ecommerce_backend.ecommerce.product.mapper;

import com.ecommerce_backend.ecommerce.product.dto.request.ProductRequestDTO;
import com.ecommerce_backend.ecommerce.product.dto.response.ProductResponseDTO;
import com.ecommerce_backend.ecommerce.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

//  @TODO: Add Remaining fields mapping
    public Product dtoToDomain(ProductRequestDTO productRequestDTO){
        var product = new Product();
        product.setProductName(productRequestDTO.getProductName());
        product.setDescription(productRequestDTO.getDescription());
        return product;
    }

    public ProductResponseDTO domainToDto(Product product){
        var productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setDescription(product.getDescription());
        return productResponseDTO;
    }


}
