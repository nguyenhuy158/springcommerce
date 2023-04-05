package com.example.springcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springcommerce.model.Role;
import com.example.springcommerce.model.UserDetailsImp;
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

		UserDetailsImp user = new UserDetailsImp();
		user.setUserName("ntqhuy2k2");
		user.setPassword(bCryptPasswordEncoder.encode("ntqhuy2k2"));
		user.setRole(Role.USER);
		user.setActive(true);

		userDetailsServiceImpl.save(user);

		UserDetailsImp admin = new UserDetailsImp();
		admin.setUserName("admin");
		admin.setPassword(bCryptPasswordEncoder.encode("admin"));
		admin.setRole(Role.ADMIN);
		admin.setActive(true);

		userDetailsServiceImpl.save(admin);
	}

}
