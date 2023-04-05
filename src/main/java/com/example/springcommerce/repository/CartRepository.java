package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springcommerce.model.Cart;
import com.example.springcommerce.model.UserDetailsImp;

@Component
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    @Transactional
    public void deleteByUserId(UserDetailsImp userId);
}
