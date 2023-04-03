package com.example.springcommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springcommerce.api.ProductControllerAPI;
import com.example.springcommerce.repository.ProductRepositoryApi;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SpringcommerceApplicationTests {

	@Autowired
	ProductControllerAPI productControllerAPI;

	@MockBean
	ProductRepositoryApi productRepositoryApi;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

}
