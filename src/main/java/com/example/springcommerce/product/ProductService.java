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
    private ProductRepository productRepository;

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;

    public List<Product> search(String keyword) {
        return productRepository.search(keyword);
    }

    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    public void save(Product user) {
        productRepository.save(user);
    }

    public Product get(Long id) throws ProductNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Long id) throws ProductNotFoundException {
        Long count = productRepository.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Could not find any products with ID " + id);
        }
        productRepository.deleteById(id);
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
        Page<Product> products = productRepository.findAll(pageable);
        return PaginatedProductResponse.builder()
                .numberOfItems(products.getTotalElements())
                .numberOfPages(products.getTotalPages())
                .products(products.getContent())
                .build();
    }

    public PaginatedProductResponse filterBooks(String keyword, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContains(keyword, pageable);
        products.and(productRepository.findAllByBrandContains(keyword, pageable));
        products.and(productRepository.findAllByColorContains(keyword, pageable));
        try {
            double parseDouble = Double.parseDouble(keyword);
            products.and(productRepository.findAllByPriceEquals(parseDouble, pageable));
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
        return PaginatedProductResponse.builder()
                .numberOfItems(products.getTotalElements())
                .numberOfPages(products.getTotalPages())
                .products(products.getContent())
                .build();
    }

    public Product getById(Long productId) {
        return productRepository.findById(productId).get();
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
