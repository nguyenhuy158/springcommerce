package com.example.springcommerce.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcommerce.product.Product;
import com.example.springcommerce.repository.ProductRepositoryApi;

@RestController
@RequestMapping("/api")
public class ProductControllerAPI {

    @Autowired
    ProductRepositoryApi productRepositoryApi;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllTutorials(@RequestParam(required = false) String name) {
        try {
            List<Product> products = new ArrayList<Product>();

            if (name == null) {
                productRepositoryApi.findAll().forEach(products::add);
            } else {
                productRepositoryApi.findByNameContaining(name).forEach(products::add);
            }
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getTutorialById(@PathVariable("id") long id) {
        Optional<Product> tutorialData = productRepositoryApi.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
        try {
            Product _tutorial = productRepositoryApi
                    .save(new Product(
                            product.getCategory(),
                            product.getName(),
                            product.getPrice(),
                            product.getBrand(),
                            product.getColor()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateTutorial(@PathVariable("id") long id,
            @RequestBody Product product) {
        Optional<Product> productData = productRepositoryApi.findById(id);

        if (productData.isPresent()) {
            Product _product = productData.get();
            _product.setCategory(product.getCategory());
            _product.setName(product.getName());
            _product.setBrand(product.getBrand());
            _product.setColor(product.getColor());
            _product.setPrice(product.getPrice());

            return new ResponseEntity<>(productRepositoryApi.save(_product),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            productRepositoryApi.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            productRepositoryApi.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
