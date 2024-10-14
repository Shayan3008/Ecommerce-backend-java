package com.ecommerce_backend.ecommerce.product.dao;

import com.ecommerce_backend.ecommerce.product.model.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDao extends CrudRepository<Attribute,Integer> {
}
