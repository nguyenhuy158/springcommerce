package com.example.springcommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringcommerceApplicationTests {

	@Test
	public void simpleTest() {
		assertEquals(1, 1);
	}

}
