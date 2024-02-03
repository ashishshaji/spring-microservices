package com.productservice.product.service;

import com.productservice.product.dto.ProductRequest;
import com.productservice.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
