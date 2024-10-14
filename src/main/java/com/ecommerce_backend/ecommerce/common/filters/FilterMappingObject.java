package com.ecommerce_backend.ecommerce.common.filters;

import jakarta.persistence.criteria.CriteriaQuery;
import lombok.Data;

@Data
public class FilterMappingObject<E> {
    
    private CriteriaQuery<E> criteriaQuery;

    private CriteriaQuery<Long> countCriteriaQuery;
}
