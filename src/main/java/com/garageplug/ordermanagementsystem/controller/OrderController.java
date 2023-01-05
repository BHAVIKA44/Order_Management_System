package com.garageplug.ordermanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.garageplug.ordermanagementsystem.dto.OrderDTO;
import com.garageplug.ordermanagementsystem.dto.ResponseDiscountDTO;
import com.garageplug.ordermanagementsystem.model.Customer;
import com.garageplug.ordermanagementsystem.utils.Constant;
import com.garageplug.ordermanagementsystem.model.Order;
import com.garageplug.ordermanagementsystem.service.CustomerService;
import com.garageplug.ordermanagementsystem.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@PostMapping("/placeOrder")
	public ResponseEntity<Object> placeOrder(@RequestBody OrderDTO orderDTO) {

		Order order = new Order();

		orderService.getTotalAmount(orderDTO.getOrderItems(), order);

		Customer customer;

		Long customerIdFromDb = customerService.isCustomerPresent(orderDTO.getCustomerEmail());
		if (customerIdFromDb != null) {
			customer = customerService.getCustomerDetailById(customerIdFromDb);

		} else {
			customer = new Customer(orderDTO.getCustomerFirstName(), orderDTO.getCustomerLastName(),
					orderDTO.getCustomerEmail(), Constant.REGULAR_CUSTOMER, new ArrayList<Order>());
		}

		List<Order> ordersList = customer.getOrders();

		orderService.applyDiscount(order, customer, ordersList.size());
		ordersList.add(order);
		customer.setOrders(ordersList);
		customerService.saveCustomer(customer);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping(value = "/discountDetails/{orderId}")
	public ResponseEntity<Object> getOrderDiscountDetails(@PathVariable Long orderId) {

		Order order = orderService.getOrderDetails(orderId);
		if (order != null) {
			Customer customer = customerService.getCustomerDetailsByOrderId(orderId);
			ResponseDiscountDTO responseDiscountDTO = new ResponseDiscountDTO(customer.getCusEmail(),
					customer.getCusFirstname(), customer.getCusLastname(), order.getDiscountPercent());
			return new ResponseEntity<>(responseDiscountDTO, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrderDetails() {

		List<Order> orderList = orderService.getAllOrders();

		return ResponseEntity.ok(orderList);
	}

	@GetMapping(value = "/{orderId}")
	public ResponseEntity<Object> getOrderDetails(@PathVariable Long orderId) {

		Order order = orderService.getOrderDetails(orderId);
		if (order != null)
			return new ResponseEntity<>(order, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
