package com.ecommerce_backend.ecommerce.common.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import com.ecommerce_backend.ecommerce.auth.JwtAuthConfig;

import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public class BaseService {
    DaoFactory daoFactory;


    MapperFactory mapperFactory;

    AuthenticationManager authenticationManager;

    JwtAuthConfig jwtAuthConfig;

    EntityManager entityManager;

    public BaseService(DaoFactory daoFactory, 
            MapperFactory mapperFactory,
            AuthenticationManager authenticationManager,
            JwtAuthConfig jwtAuthConfig,EntityManager entityManager) {
        this.daoFactory = daoFactory;
        this.mapperFactory = mapperFactory;
        this.authenticationManager = authenticationManager;
        this.jwtAuthConfig = jwtAuthConfig;
        this.entityManager = entityManager;
    }
}
