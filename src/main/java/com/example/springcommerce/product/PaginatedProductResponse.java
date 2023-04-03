package com.example.springcommerce.product;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedProductResponse {
    private List<Product> products;
    private Long numberOfItems;
    private int numberOfPages;
}
