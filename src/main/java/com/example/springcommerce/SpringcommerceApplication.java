package com.example.springcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springcommerce.emun.Role;
import com.example.springcommerce.service.UserDetailsServiceImpl;

@SpringBootApplication
public class SpringcommerceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringcommerceApplication.class, args);
	}

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// add test user with Role admin and user
		if (userDetailsServiceImpl.isUsernameUnique("ntqhuy2k2")) {
			userDetailsServiceImpl.saveWithUsernameAndRawPassword("ntqhuy2k2", "ntqhuy2k2", Role.USER);
		}

		if (userDetailsServiceImpl.isUsernameUnique("admin")) {
			userDetailsServiceImpl.saveWithUsernameAndRawPassword("admin", "admin", Role.ADMIN);
		}
	}

}
