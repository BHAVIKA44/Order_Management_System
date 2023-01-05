package com.garageplug.ordermanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "product_id")
	private Long productId;

	public OrderItem(int quantity, Long productId) {
		super();
		this.quantity = quantity;
		this.productId = productId;
	}

}
