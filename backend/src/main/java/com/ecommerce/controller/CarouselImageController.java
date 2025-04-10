package com.ecommerce.controller;

import com.ecommerce.model.CarouselImage;
import com.ecommerce.service.CarouselImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carousel")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class CarouselImageController {

    private static final Logger logger = LoggerFactory.getLogger(CarouselImageController.class);

    @Autowired
    private CarouselImageService carouselImageService;

    @GetMapping
    public ResponseEntity<List<CarouselImage>> getAllCarouselImages() {
        logger.info("Received request to get all carousel images");
        try {
            List<CarouselImage> carouselImages = carouselImageService.getAllCarouselImages();
            logger.info("Successfully retrieved {} carousel images", carouselImages.size());
            return ResponseEntity.ok(carouselImages);
        } catch (Exception e) {
            logger.error("Error retrieving carousel images: ", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<CarouselImage>> getActiveCarouselImages() {
        logger.info("Received request to get active carousel images");
        try {
            List<CarouselImage> carouselImages = carouselImageService.getActiveCarouselImages();
            logger.info("Successfully retrieved {} active carousel images", carouselImages.size());
            return ResponseEntity.ok(carouselImages);
        } catch (Exception e) {
            logger.error("Error retrieving active carousel images: ", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarouselImage> getCarouselImageById(@PathVariable Long id) {
        logger.info("Received request to get carousel image by id: {}", id);
        try {
            CarouselImage carouselImage = carouselImageService.getCarouselImageById(id);
            logger.info("Successfully retrieved carousel image with id: {}", id);
            return ResponseEntity.ok(carouselImage);
        } catch (Exception e) {
            logger.error("Error retrieving carousel image with id {}: ", id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<CarouselImage> createCarouselImage(@RequestBody CarouselImage carouselImage) {
        logger.info("Received request to create carousel image");
        try {
            CarouselImage savedImage = carouselImageService.createCarouselImage(carouselImage);
            logger.info("Successfully created carousel image with id: {}", savedImage.getId());
            return ResponseEntity.ok(savedImage);
        } catch (Exception e) {
            logger.error("Error creating carousel image: ", e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarouselImage> updateCarouselImage(
            @PathVariable Long id,
            @RequestBody CarouselImage carouselImage) {
        logger.info("Received request to update carousel image with id: {}", id);
        try {
            CarouselImage updatedImage = carouselImageService.updateCarouselImage(id, carouselImage);
            logger.info("Successfully updated carousel image with id: {}", id);
            return ResponseEntity.ok(updatedImage);
        } catch (Exception e) {
            logger.error("Error updating carousel image with id {}: ", id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarouselImage(@PathVariable Long id) {
        logger.info("Received request to delete carousel image with id: {}", id);
        try {
            carouselImageService.deleteCarouselImage(id);
            logger.info("Successfully deleted carousel image with id: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting carousel image with id {}: ", id, e);
            return ResponseEntity.status(500).build();
        }
    }
} 