package com.ecommerce_backend.ecommerce.common.beans;

import org.springframework.stereotype.Component;

import com.ecommerce_backend.ecommerce.auth.dao.UsersDAO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Component
public class DaoFactory {
    
    private final UsersDAO usersDAO;
}
