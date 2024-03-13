package com.tutorial.apidemo.Springboot.demo.controllers;

import com.tutorial.apidemo.Springboot.demo.models.Product;
import com.tutorial.apidemo.Springboot.demo.models.ResponseObject;
import com.tutorial.apidemo.Springboot.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    // DI = Dependency Injection
    @Autowired
    private ProductRepository repository;
    private Product newProdcut;

    @GetMapping("")
        // this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll(); // Where is data?
        // You must save this to Database, Now we have H2 DB = In-memory
    }

    // Get details product
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query product successfully", foundProduct)) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "Cannot find product with id: " + id, ""));
    }

    // Insert new product with POST method
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProdcut) {
        List<Product> foundProduct = repository.findByProductName(newProdcut.getProductName().trim());
        if (foundProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("falied", "This product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert Product successfully", repository.save(newProdcut))
        );
    }

    // Update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable UUID id) {
        Product updatedProduct = repository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setYear(newProduct.getYear());
            product.setPrice(newProduct.getPrice());
            product.setUrl(newProduct.getUrl());
            return repository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updatedProduct)
        );
    }

    // Delete product
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable UUID id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Delete product successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Cannot found product with id: " + id, ""));
    }
}
