package com.ecommerce_backend.ecommerce.product.dao;

import com.ecommerce_backend.ecommerce.product.model.AttributeValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeValueDao extends CrudRepository<AttributeValue,Integer> {
}
