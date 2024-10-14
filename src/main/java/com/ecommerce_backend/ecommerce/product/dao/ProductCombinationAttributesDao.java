package com.ecommerce_backend.ecommerce.product.dao;

import com.ecommerce_backend.ecommerce.product.model.ProductCombinationAttributes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCombinationAttributesDao extends CrudRepository<ProductCombinationAttributes,Integer>{
}
