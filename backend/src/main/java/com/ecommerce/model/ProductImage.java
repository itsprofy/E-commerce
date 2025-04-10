package com.ecommerce.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Entity class representing additional images for a product
@Data
@Entity
@Table(name = "product_images")
public class ProductImage {
    // Primary key for the product image
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Image URL with validation constraints
    @NotBlank(message = "Image URL is required")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // Product relationship (many-to-one)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Display order for sorting product images
    private Integer displayOrder;

    // Flag indicating if the image is active/visible
    private boolean active = true;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
} 