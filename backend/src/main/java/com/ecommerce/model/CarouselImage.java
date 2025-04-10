package com.ecommerce.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Entity class representing a carousel image for the homepage
@Entity
@Data
@Table(name = "carousel_images")
public class CarouselImage {
    // Primary key for the carousel image
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Image URL with validation constraints
    @NotBlank(message = "Image URL is required")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    // Optional title for the carousel image
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;
    
    // Optional subtitle for the carousel image
    @Size(max = 200, message = "Subtitle must be less than 200 characters")
    private String subtitle;
    
    // Display order for sorting carousel images
    @Column(name = "display_order")
    private Integer displayOrder;
    
    // Flag indicating if the image is active/visible
    private Boolean active = true;
} 