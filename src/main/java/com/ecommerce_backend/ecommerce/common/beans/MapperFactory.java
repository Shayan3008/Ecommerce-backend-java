package com.ecommerce_backend.ecommerce.common.beans;

import com.ecommerce_backend.ecommerce.product.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import com.ecommerce_backend.ecommerce.auth.mappers.UserMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Component
public class MapperFactory {
    private final UserMapper userMapper;
    private final ProductMapper productMapper;
}
