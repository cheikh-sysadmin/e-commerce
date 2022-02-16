package com.atomtech.ecommerce.repository;

import com.atomtech.ecommerce.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findById(long img_id);
}
