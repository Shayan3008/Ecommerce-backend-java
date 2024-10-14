package com.ecommerce_backend.ecommerce.product.dao;

import com.ecommerce_backend.ecommerce.product.model.ProductPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceDao extends CrudRepository<ProductPrice,Integer> {
}
