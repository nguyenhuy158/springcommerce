package com.example.springcommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcommerce.model.UserDetailsImp;

public interface UserDetailImpRepository extends JpaRepository<UserDetailsImp, String> {
    Optional<UserDetailsImp> findByEmail(String email);

}