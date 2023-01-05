package com.garageplug.ordermanagementsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.garageplug.ordermanagementsystem.model.Customer;
import com.garageplug.ordermanagementsystem.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer getCustomerDetailsByOrderId(Long orderId) {
		Customer customer = this.customerRepository.findByOrdersOrderid(orderId);
		return customer;
	}

	public List<Customer> getAllCustomers() {
		return this.customerRepository.findAll();
	}

	public Long isCustomerPresent(String email) {
		Customer customer1 = customerRepository.findByCusEmail(email);
		return customer1 != null ? customer1.getCustomerid() : null;
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer getCustomerDetailById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.isPresent() ? customer.get() : null;
	}

}
