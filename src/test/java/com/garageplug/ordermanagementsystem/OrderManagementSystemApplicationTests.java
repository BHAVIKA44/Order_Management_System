package com.garageplug.ordermanagementsystem;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.garageplug.ordermanagementsystem.model.Customer;
import com.garageplug.ordermanagementsystem.repository.CustomerRepository;
import com.garageplug.ordermanagementsystem.service.CustomerService;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.garageplug.ordermanagementsystem.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderManagementSystemApplicationTests {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;

	@Test
	public void getAllCustomersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Customer("john", "parker", "john@gmail.com", "Regular",new ArrayList<Order>()), new Customer("peter", "parker", "peter@gmail.com", "Regular",new ArrayList<Order>())).collect(Collectors.toList()));
		assertEquals(2, service.getAllCustomers().size());
	}

	
	
	

}
