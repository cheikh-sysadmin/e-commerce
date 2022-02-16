package com.atomtech.ecommerce.repository;

import com.atomtech.ecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    List<OrderDetails> findById(long od_id);

    void deleteById(long od_id);
}
