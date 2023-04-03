package com.example.springcommerce.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> search(String keyword) {
        return repo.search(keyword);
    }

    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    public void save(Product user) {
        repo.save(user);
    }

    public Product get(Integer id) throws ProductNotFoundException {
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }
}
