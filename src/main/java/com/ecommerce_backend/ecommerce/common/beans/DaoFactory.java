package com.ecommerce_backend.ecommerce.common.beans;

import com.ecommerce_backend.ecommerce.auth.dao.UsersDAO;
import com.ecommerce_backend.ecommerce.product.dao.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class DaoFactory {
    
    private final UsersDAO usersDAO;
    private final ProductDao productDao;
    private final AttributeDao attributeDao;
    private final ProductImageDao productImageDao;
    private final ProductPriceDao productPriceDao;
    private final AttributeValueDao attributeValueDao;
    private final ProductCombinationDao productCombinationDao;
    private final ProductCombinationAttributesDao productCombinationAttributesDao;
}
