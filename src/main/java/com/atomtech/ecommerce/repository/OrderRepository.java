package com.atomtech.ecommerce.repository;

import com.atomtech.ecommerce.model.Orders;
import com.atomtech.ecommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    Orders findById(long orderId);
}
