package com.ecommerce.service;

import com.ecommerce.model.CarouselImage;
import com.ecommerce.repository.CarouselImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarouselImageService {

    @Autowired
    private CarouselImageRepository carouselImageRepository;

    public List<CarouselImage> getAllCarouselImages() {
        return carouselImageRepository.findAllByOrderByDisplayOrderAsc();
    }

    public List<CarouselImage> getActiveCarouselImages() {
        return carouselImageRepository.findByActiveTrue();
    }

    public CarouselImage getCarouselImageById(Long id) {
        return carouselImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carousel image not found with id: " + id));
    }

    @Transactional
    public CarouselImage createCarouselImage(CarouselImage carouselImage) {
        // Set a default display order if none is provided
        if (carouselImage.getDisplayOrder() == null) {
            long count = carouselImageRepository.count();
            carouselImage.setDisplayOrder((int) count + 1);
        }
        
        // Set active to true by default if not specified
        if (carouselImage.getActive() == null) {
            carouselImage.setActive(true);
        }
        
        return carouselImageRepository.save(carouselImage);
    }

    @Transactional
    public CarouselImage updateCarouselImage(Long id, CarouselImage carouselImage) {
        CarouselImage existingImage = getCarouselImageById(id);
        
        existingImage.setImageUrl(carouselImage.getImageUrl());
        existingImage.setTitle(carouselImage.getTitle());
        existingImage.setSubtitle(carouselImage.getSubtitle());
        existingImage.setDisplayOrder(carouselImage.getDisplayOrder());
        existingImage.setActive(carouselImage.getActive());
        
        return carouselImageRepository.save(existingImage);
    }

    @Transactional
    public void deleteCarouselImage(Long id) {
        CarouselImage image = getCarouselImageById(id);
        carouselImageRepository.delete(image);
    }
} 