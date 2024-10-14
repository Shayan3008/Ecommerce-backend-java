package com.ecommerce_backend.ecommerce.product.controller;

import com.ecommerce_backend.ecommerce.common.config.CommonResponse;
import com.ecommerce_backend.ecommerce.common.filters.ListingResponseObject;
import com.ecommerce_backend.ecommerce.product.dto.request.ProductRequestDTO;
import com.ecommerce_backend.ecommerce.product.dto.response.ProductResponseDTO;
import com.ecommerce_backend.ecommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<CommonResponse<ListingResponseObject<ProductResponseDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String search
    ) throws ParseException {
        var listingResponseObject = productService.getAllProducts(page, size,search);
        var commonResponse = new CommonResponse<>("All Products", 200, listingResponseObject);
        return ResponseEntity.ok(commonResponse);
    }


    @PostMapping("/save")
    public ResponseEntity<CommonResponse<String>> saveProduct(@RequestBody ProductRequestDTO productRequestDTO){
        var responseString = productService.saveProduct(productRequestDTO);
        return ResponseEntity.ok(new CommonResponse<>(responseString,200,responseString));
    }
}
