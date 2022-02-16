package com.atomtech.ecommerce.repository;

import com.atomtech.ecommerce.model.Product;
import com.atomtech.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
        Product findById(long prod_id);
}

