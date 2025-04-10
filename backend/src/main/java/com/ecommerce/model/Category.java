package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.validation.constraints.*;

// Entity class representing a product category in the e-commerce system
@Data
@Entity
@Table(name = "categories")
public class Category {
    // Primary key for the category
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Category name with validation constraints
    @NotBlank(message = "Category name is required")
    @Column(nullable = false, unique = true)
    private String name;

    // Category description with validation constraints
    @NotBlank(message = "Category description is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("category")
    private List<Product> products = new ArrayList<>();
} 