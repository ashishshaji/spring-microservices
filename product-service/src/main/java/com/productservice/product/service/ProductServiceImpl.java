package com.productservice.product.service;

import com.productservice.product.entity.Product;
import com.productservice.product.dto.ProductRequest;
import com.productservice.product.dto.ProductResponse;
import com.productservice.product.repository.ProductRepo;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@Builder
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepo productRepo;
    @Override
    public void createProduct(ProductRequest productRequest) {

        Product product=Product.builder()
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        productRepo.save(product);
        log.info("Product {} is saved", product.getId());

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products=productRepo.findAll();


        return products.stream().map(this::mapToProductResponse).toList();

    }
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
