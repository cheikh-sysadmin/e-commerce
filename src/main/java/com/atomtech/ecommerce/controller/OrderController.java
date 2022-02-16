package com.atomtech.ecommerce.controller;

import com.atomtech.ecommerce.model.Orders;
import com.atomtech.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Orders addOrder(@RequestBody Orders order ){
        return  orderService.addOrder(order);
    }

    @GetMapping
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable("orderId") long orderId){
        return orderService.deleteOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOne(@PathVariable long orderId) {
        return orderService.getOne(orderId);
    }
}
