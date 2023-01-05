package com.garageplug.ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garageplug.ordermanagementsystem.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByCusEmail(String email);

	public Customer findByOrdersOrderid(Long id);

}
