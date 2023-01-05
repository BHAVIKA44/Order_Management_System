package com.garageplug.ordermanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garageplug.ordermanagementsystem.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
