package com.example.springcommerce.api;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.springcommerce.model.Order;
import com.example.springcommerce.product.Product;
import com.example.springcommerce.repository.OrderRepositoryApi;
import com.example.springcommerce.repository.ProductRepositoryApi;

@RestController
@RequestMapping("/api")
public class OrderControllerAPI {
    @Autowired
    private ProductRepositoryApi productRepositoryApi;

    @Autowired
    private OrderRepositoryApi orderRepositoryApi;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllTags() {
        List<Order> tags = new ArrayList<Order>();

        orderRepositoryApi.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createTag(@RequestBody Order orderRequest) {

        Order _order = orderRepositoryApi
                .save(new Order(orderRequest.getProducts(),
                        orderRequest.getFullName(),
                        orderRequest.getPhone(),
                        orderRequest.getAddress()));
        return new ResponseEntity<>(_order, HttpStatus.CREATED);
    }

    @GetMapping("/products/{productId}/orders")
    public ResponseEntity<List<Order>> getAllTagsByTutorialId(@PathVariable(value = "productId") Long productId) {
        if (!productRepositoryApi.existsById(productId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + productId);
        }

        List<Order> tags = orderRepositoryApi.findOrdersByProductsId(productId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getTagsById(@PathVariable(value = "id") Long id) {
        Order orders = orderRepositoryApi.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}/products")
    public ResponseEntity<List<Product>> getAllTutorialsByTagId(@PathVariable(value = "orderId") Long orderId) {
        if (!orderRepositoryApi.existsById(orderId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + orderId);
        }

        List<Product> products = productRepositoryApi.findProductsByOrdersId(orderId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products/{productId}/orders")
    public ResponseEntity<Order> addTag(@PathVariable(value = "productId") Long productId,
            @RequestBody Order orderRequest) {
        Order tag = productRepositoryApi.findById(productId).map(product -> {
            Long tagId = orderRequest.getId();

            // tag is existed
            if (tagId != null && tagId != 0L) {
                Order _tag = orderRepositoryApi.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " +
                                tagId));
                product.addToOrder(_tag);
                productRepositoryApi.save(product);
                return _tag;
            }

            // add and create new Tag
            product.addToOrder(orderRequest);
            return orderRepositoryApi.save(orderRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + productId));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateTag(@PathVariable("id") long id, @RequestBody Order tagRequest) {
        Order tag = orderRepositoryApi.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

        tag.setFullName(tagRequest.getFullName() != null ? tagRequest.getFullName() : tag.getFullName());
        tag.setOrderDate(tagRequest.getOrderDate() != null ? tagRequest.getOrderDate() : tag.getOrderDate());
        tag.setPhone(tagRequest.getPhone() != null ? tagRequest.getPhone() : tag.getPhone());
        tag.setAddress(tagRequest.getAddress() != null ? tagRequest.getAddress() : tag.getAddress());
        tag.setProducts(tagRequest.getProducts() != null ? tagRequest.getProducts() : tag.getProducts());

        return new ResponseEntity<>(orderRepositoryApi.save(tag), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}/orders/{orderId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "productId") Long productId,
            @PathVariable(value = "orderId") Long orderId) {
        Product product = productRepositoryApi.findById(
                productId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + productId));

        product.removeToOrder(orderId);
        productRepositoryApi.save(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        orderRepositoryApi.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}