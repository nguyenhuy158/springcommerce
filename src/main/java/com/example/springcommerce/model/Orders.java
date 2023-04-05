package com.example.springcommerce.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = {
    // CascadeType.PERSIST,
    // CascadeType.MERGE
    // }, mappedBy = "orders")
    // @JsonIgnore
    // private List<Product> products = new ArrayList<>();

    @Column(name = "orderDate", nullable = false, updatable = false)
    @CreationTimestamp
    private Date orderDate;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Customer_Name", length = 255, nullable = false)
    private String customerName;

    @Column(name = "Customer_Address", length = 255, nullable = false)
    private String customerAddress;

    @Column(name = "Customer_Email", length = 128, nullable = false)
    private String customerEmail;

    @Column(name = "Customer_Phone", length = 128, nullable = false)
    private String customerPhone;

}
