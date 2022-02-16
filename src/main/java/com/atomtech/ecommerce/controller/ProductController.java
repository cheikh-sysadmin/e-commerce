package com.atomtech.ecommerce.controller;

import com.atomtech.ecommerce.model.Product;
import com.atomtech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/products")
    public ResponseEntity<Object> add(@RequestBody Product product) {
        return productService.add(product);
    }

    @PutMapping(value = "/products/{prod_id}")
    public ResponseEntity<Object> update(@PathVariable long prod_id, @RequestBody Product product) {
        return productService.update(prod_id, product);
    }

    @GetMapping(value = "/products")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(value = "/products/{prod_id}")
    public ResponseEntity<Object> getOne(@PathVariable long prod_id) {
        return productService.getOne(prod_id);
    }
}
