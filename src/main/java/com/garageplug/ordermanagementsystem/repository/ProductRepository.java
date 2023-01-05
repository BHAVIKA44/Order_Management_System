package com.garageplug.ordermanagementsystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.garageplug.ordermanagementsystem.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public Optional<Product> findById(Long id);
}
