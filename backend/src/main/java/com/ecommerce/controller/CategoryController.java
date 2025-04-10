package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class CategoryController {
    
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Received request to get all categories");
        try {
            List<Category> categories = categoryService.getAllCategories();
            if (categories == null || categories.isEmpty()) {
                logger.warn("No categories found in the database");
                return ResponseEntity.ok(List.of());
            }
            logger.info("Successfully retrieved {} categories", categories.size());
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            logger.error("Error retrieving categories: ", e);
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        logger.info("Received request to get category by id: {}", id);
        try {
            Category category = categoryService.getCategoryById(id);
            logger.info("Successfully retrieved category with id: {}", id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            logger.error("Error retrieving category with id {}: ", id, e);
            return ResponseEntity.status(500).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        logger.info("Received request to create category: {}", category.getName());
        try {
            Category savedCategory = categoryService.createCategory(category);
            logger.info("Successfully created category with id: {}", savedCategory.getId());
            return ResponseEntity.ok(savedCategory);
        } catch (Exception e) {
            logger.error("Error creating category: ", e);
            
            // Return a more detailed error message
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Category name") || errorMessage.contains("Category description")) {
                return ResponseEntity.badRequest().body(errorMessage);
            }
            
            return ResponseEntity.status(500).body("Error creating category: " + errorMessage);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {
        logger.info("Received request to update category with id: {}", id);
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            logger.info("Successfully updated category with id: {}", id);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            logger.error("Error updating category with id {}: ", id, e);
            
            // Return a more detailed error message
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Category name") || errorMessage.contains("Category description")) {
                return ResponseEntity.badRequest().body(errorMessage);
            }
            
            return ResponseEntity.status(500).body("Error updating category: " + errorMessage);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        logger.info("Received request to delete category with id: {}", id);
        try {
            categoryService.deleteCategory(id);
            logger.info("Successfully deleted category with id: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting category with id {}: ", id, e);
            return ResponseEntity.status(500).build();
        }
    }
} 