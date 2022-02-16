package com.atomtech.ecommerce.service;

import com.atomtech.ecommerce.model.OrderDetails;
import com.atomtech.ecommerce.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public OrderDetails addOrderDetails(OrderDetails orderDetails){
        try
        {
            return orderDetailsRepository.save(orderDetails);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return null;
        }

    }

    public List<OrderDetails> getAll(){
        return orderDetailsRepository.findAll();
    }

    public List<OrderDetails> getById(long od_id){
        return orderDetailsRepository.findById(od_id);
    }

    public String delete(long od_id){
        orderDetailsRepository.deleteById(od_id);
        return "Deleted!";
    }
}
