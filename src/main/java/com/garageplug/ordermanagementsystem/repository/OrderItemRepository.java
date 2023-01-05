package com.garageplug.ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garageplug.ordermanagementsystem.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
