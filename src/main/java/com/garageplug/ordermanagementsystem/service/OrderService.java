package com.garageplug.ordermanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garageplug.ordermanagementsystem.dto.OrderItemDTO;
import com.garageplug.ordermanagementsystem.model.Customer;
import com.garageplug.ordermanagementsystem.model.Order;
import com.garageplug.ordermanagementsystem.model.OrderItem;
import com.garageplug.ordermanagementsystem.model.Product;
import com.garageplug.ordermanagementsystem.repository.OrderItemRepository;
import com.garageplug.ordermanagementsystem.repository.OrderRepository;
import com.garageplug.ordermanagementsystem.repository.ProductRepository;
import com.garageplug.ordermanagementsystem.utils.Constant;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	public Order getOrderDetails(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.isPresent() ? order.get() : null;
	}

	public void getTotalAmount(List<OrderItemDTO> orderItemList, Order order) {

		double totalItemAmount = 0;
		double singleItemAmount = 0;
		int availableQuantity = 0;
		List<OrderItem> itemsList = new ArrayList<>();
		for (OrderItemDTO item : orderItemList) {

			Long productId = item.getProductId();
			Optional<Product> product = productRepository.findById(productId);
			if (product.isPresent()) {
				Product product1 = product.get();
				if (product1.getAvailableQuantity() < item.getQuantity()) {
					singleItemAmount = product1.getProductPrice() * product1.getAvailableQuantity();
					item.setQuantity(product1.getAvailableQuantity());
				} else {
					singleItemAmount = item.getQuantity() * product1.getProductPrice();
					availableQuantity = product1.getAvailableQuantity() - item.getQuantity();
				}
				totalItemAmount = totalItemAmount + singleItemAmount;
				product1.setAvailableQuantity(availableQuantity);
				availableQuantity = 0;

				productRepository.save(product1);
				OrderItem orderItem = new OrderItem(item.getQuantity(), item.getProductId());
				itemsList.add(orderItem);

			}

		}
		order.setOrderItems(itemsList);
		order.setTotalPrice(totalItemAmount);
	}

	public void applyDiscount(Order order, Customer customer, int ordersCount) {
		if (ordersCount >= 9 && ordersCount < 19) {
			order.setDiscountPercent(Constant.DISCOUNT_PERCENT_GOLD);
			customer.setCusCategory(Constant.GOLD_CUSTOMER);
		} else if (ordersCount >= 19) {
			order.setDiscountPercent(Constant.DISCOUNT_PERCENT_PLATINUM);
			customer.setCusCategory(Constant.PLATINUM_CUSTOMER);
		} else if (ordersCount == 8) {
			sendMail(Constant.GOLD_CUSTOMER);
		} else if (ordersCount == 18) {
			sendMail(Constant.PLATINUM_CUSTOMER);
		}
	}

	public void sendMail(String offer) {

	}
}
