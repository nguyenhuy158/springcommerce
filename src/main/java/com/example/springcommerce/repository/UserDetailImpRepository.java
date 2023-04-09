package com.example.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcommerce.model.UserDetailsImp;

public interface UserDetailImpRepository extends JpaRepository<UserDetailsImp, String> {

    Long countByUserName(String username);

}
