package com.example.springcommerce.product;

import java.util.ArrayList;
import java.util.List;

import com.example.springcommerce.model.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String category;
    private String name;
    private Double price;
    private String brand;
    private String color;

    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private String image;

    // @Column(name = "timestamp", nullable = false, updatable = false, insertable =
    // false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    // public Timestamp timestamp;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "product_orders", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
            @JoinColumn(name = "order_id") })
    private List<Order> orders = new ArrayList<>();

    public void addToOrder(Order order) {
        this.orders.add(order);
        order.getProducts().add(this);
    }

    public void removeToOrder(Long orderId) {
        Order order = this.orders.stream().filter(t -> t.getId() == orderId).findFirst().orElse(null);
        if (order != null) {
            this.orders.remove(order);
            order.getProducts().remove(this);
        }
    }

    public Product(String category, String name, Double price, String brand,
            String color) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.color = color;
    }

}
