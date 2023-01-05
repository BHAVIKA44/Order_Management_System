package com.garageplug.ordermanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.garageplug.ordermanagementsystem.model.OrderItem;
import com.garageplug.ordermanagementsystem.repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}
}
