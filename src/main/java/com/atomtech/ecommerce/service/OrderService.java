package com.atomtech.ecommerce.service;

import com.atomtech.ecommerce.model.Orders;
import com.atomtech.ecommerce.model.Users;
import com.atomtech.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository ordersRepository;

    @Autowired
    public OrderService(OrderRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public Orders addOrder(Orders order){
        Date date = new Date();
        order.setOrderDate(date);
        try
        {
            return ordersRepository.save(order);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return null;
        }

    }

    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    public String deleteOrder(long id){
        ordersRepository.deleteById(id);
        return "Deleted";
    }

    public ResponseEntity<Object> getOne(@PathVariable long id) {
        Orders orders = ordersRepository.findById(id);
        if (orders == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette commande n'existe pas !'}");

        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/users")
                .buildAndExpand(orders.getOrderId())
                .toUri();

//        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(user_id)).withRel("rvList");
//        users.add(link);

        return ResponseEntity.created(location).body(orders);
    }
}
