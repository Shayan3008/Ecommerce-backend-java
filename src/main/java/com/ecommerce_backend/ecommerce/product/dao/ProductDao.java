package com.ecommerce_backend.ecommerce.product.dao;

import com.ecommerce_backend.ecommerce.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product,Integer>,CrudRepository<Product,Integer> {
}
