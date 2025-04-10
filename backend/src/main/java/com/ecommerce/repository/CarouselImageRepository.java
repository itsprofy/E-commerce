package com.ecommerce.repository;

import com.ecommerce.model.CarouselImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarouselImageRepository extends JpaRepository<CarouselImage, Long> {
    List<CarouselImage> findByActiveTrue();
    List<CarouselImage> findAllByOrderByDisplayOrderAsc();
} 