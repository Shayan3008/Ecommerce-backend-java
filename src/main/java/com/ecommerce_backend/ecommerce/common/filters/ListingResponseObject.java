package com.ecommerce_backend.ecommerce.common.filters;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListingResponseObject<E> {
    private List<E> response;
    private Long totalResultCount;
}
