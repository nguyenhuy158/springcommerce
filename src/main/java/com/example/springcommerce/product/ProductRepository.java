package com.example.springcommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // @Query("SELECT p FROM products p WHERE p.name LIKE %?1%"
    // + " OR p.brand LIKE %?1%"
    // + " OR p.color LIKE %?1%"
    // + " OR CONCAT(p.price, '') LIKE %?1%"
    // )
    @Query(value = "SELECT * FROM products p WHERE p.name LIKE %?1%"
            + " OR p.brand LIKE %?1%"
            + " OR p.color LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%", countQuery = "SELECT count(*) FROM products p WHERE p.name LIKE %?1%"
                    + " OR p.brand LIKE %?1%"
                    + " OR p.color LIKE %?1%"
                    + " OR CONCAT(p.price, '') LIKE %?1%", nativeQuery = true)

    public List<Product> search(String keyword);

    public Long countById(Integer id);
}
