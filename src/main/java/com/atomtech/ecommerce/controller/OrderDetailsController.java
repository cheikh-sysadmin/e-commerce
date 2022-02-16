package com.atomtech.ecommerce.controller;

import com.atomtech.ecommerce.model.OrderDetails;
import com.atomtech.ecommerce.service.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping
    public OrderDetails add(@RequestBody OrderDetails orderDetails){
        return orderDetailsService.addOrderDetails(orderDetails);
    }

    @GetMapping
    public List<OrderDetails> all(){
        return orderDetailsService.getAll();
    }

    @GetMapping("/{od_id}")
    public List<OrderDetails> GetById(@PathVariable("od_id") long od_id){
        return orderDetailsService.getById(od_id);
    }

    @DeleteMapping("{od_id}")
    public String delete(@PathVariable("od_id") long od_id){
        return orderDetailsService.delete(od_id);
    }
}
