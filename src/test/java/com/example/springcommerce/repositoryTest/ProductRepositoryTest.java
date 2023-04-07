package com.example.springcommerce.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springcommerce.product.Product;
import com.example.springcommerce.product.ProductRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testAddProduct() {
        Product expectedProduct = new Product("Food", "Pho", (double) 23000, "Viet Name", "No color");
        Long id = productRepository.save(expectedProduct).getId();

        Product actualProduct = productRepository.findById(id).get();

        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getName(), actualProduct.getName());
    }
}
