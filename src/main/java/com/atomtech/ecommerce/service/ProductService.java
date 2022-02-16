package com.atomtech.ecommerce.service;

import com.atomtech.ecommerce.model.Product;
import com.atomtech.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
@Transactional
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public ResponseEntity<Object> add(@RequestBody Product product) {

        try
        {
            Product product1 = productRepository.save(product);

            if(product1 == null)
                return ResponseEntity.noContent().build();

            return ResponseEntity.status(201).body(product1);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }

    }



    public ResponseEntity<Object> update(@PathVariable long prod_id, @RequestBody Product product) {

        try
        {
            Product product1 = productRepository.findById(prod_id);

            if(product1 == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Ce produit n'existe pas !'}");

            product.setProd_id(product1.getProd_id());
            product1 = productRepository.save(product);
            if(product1 == null)
                return ResponseEntity.noContent().build();

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(product1.getProd_id())
                    .toUri();

            return ResponseEntity.ok(location);
        }
        catch (Exception e)
        {
            System.out.println("AAS::::) ERROR: "+e.getMessage());
            return ResponseEntity.noContent().build();
        }

    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> getOne(@PathVariable long prod_id) {
        Product product = productRepository.findById(prod_id);
        if (product == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette utilisateur n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/prduits")
                .buildAndExpand(product.getProd_id())
                .toUri();

//        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(user_id)).withRel("rvList");
//        users.add(link);

        return ResponseEntity.created(location).body(product);
    }


}
