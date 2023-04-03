package com.example.springcommerce.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;

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

    public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> pagedResult = productPagingAndSortingRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }

    public PaginatedProductResponse readProducts(Pageable pageable) {
        Page<Product> products = repo.findAll(pageable);
        return PaginatedProductResponse.builder()
                .numberOfItems(products.getTotalElements())
                .numberOfPages(products.getTotalPages())
                .products(products.getContent())
                .build();
    }

    // public Page<Product> findPaginated(Integer currentPage, Integer pageSize,
    // String sortBy) {

    // // List<Product> products = repo.findAll();

    // // Pageable of = PageRequest.of(currentPage, pageSize, Sort.by(sortBy));

    // // int startItem = currentPage * pageSize;
    // // List<Product> list;

    // // if (products.size() < startItem) {
    // // list = Collections.emptyList();
    // // } else {
    // // int toIndex = Math.min(startItem + pageSize, products.size());
    // // list = products.subList(startItem, toIndex);
    // // }

    // // return new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize),
    // // products.size());
    // }
}
