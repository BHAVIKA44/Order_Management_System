package com.garageplug.ordermanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.garageplug.ordermanagementsystem.model.Customer;
import com.garageplug.ordermanagementsystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomerDetails() {

		List<Customer> customerList = customerService.getAllCustomers();

		return ResponseEntity.ok(customerList);
	}

	@GetMapping(value = "/{customerId}")
	public ResponseEntity<Object> getCustomerDetails(@PathVariable Long customerId) {

		Customer customer = customerService.getCustomerDetailById(customerId);
		if (customer != null)
			return new ResponseEntity<>(customer, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping(value = "/order/{orderId}")
	public ResponseEntity<Object> getCustomerDetailsByOrderId(@PathVariable Long orderId) {

		Customer customer = customerService.getCustomerDetailsByOrderId(orderId);
		if (customer != null)
			return new ResponseEntity<>(customer, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
