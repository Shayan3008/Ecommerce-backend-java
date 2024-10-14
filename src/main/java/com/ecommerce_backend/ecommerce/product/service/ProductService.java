package com.ecommerce_backend.ecommerce.product.service;

import com.ecommerce_backend.ecommerce.common.beans.DaoFactory;
import com.ecommerce_backend.ecommerce.common.beans.MapperFactory;
import com.ecommerce_backend.ecommerce.common.filters.CriteriaFilter;
import com.ecommerce_backend.ecommerce.common.filters.ListingResponseObject;
import com.ecommerce_backend.ecommerce.common.utils.HelperUtils;
import com.ecommerce_backend.ecommerce.product.dto.request.ProductRequestDTO;
import com.ecommerce_backend.ecommerce.product.dto.response.ProductResponseDTO;
import com.ecommerce_backend.ecommerce.product.model.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final DaoFactory daoFactory;
    private final MapperFactory mapperFactory;
    private final EntityManager em;

    public ListingResponseObject<ProductResponseDTO> getAllProducts(int page, int size, String search) throws ParseException {
        if (search.isEmpty()) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Product> products = daoFactory.getProductDao().findAll(pageRequest);
            return new ListingResponseObject<>(products.getContent().stream().map(product -> mapperFactory.getProductMapper().
                    domainToDto(product)).toList(), products.getTotalElements());
        }

        CriteriaFilter<Product> criteriaFilter = new CriteriaFilter<>();
        var listingObject = criteriaFilter.applyFilterForSearching(Product.class, HelperUtils.listToMap(search), em, null, size, page);
        return new ListingResponseObject<>(listingObject.getResponse().stream().map(product -> mapperFactory.getProductMapper().
                domainToDto(product)).toList(), listingObject.getTotalResultCount());
    }

    public String saveProduct(ProductRequestDTO productRequestDTO) {
        Product product = mapperFactory.getProductMapper().dtoToDomain(productRequestDTO);
        daoFactory.getProductDao().save(product);
        return "Product Saved Successfully";
    }

}
