package com.productservice.product.entity;
import jakarta.persistence.*;


import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    private BigDecimal price;
}
