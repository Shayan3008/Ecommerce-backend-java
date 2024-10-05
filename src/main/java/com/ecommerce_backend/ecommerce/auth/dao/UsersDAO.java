package com.ecommerce_backend.ecommerce.auth.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce_backend.ecommerce.auth.models.Users;

@Repository
public interface UsersDAO extends CrudRepository<Users, Integer>{
    Users findByEmail(String email);
}
