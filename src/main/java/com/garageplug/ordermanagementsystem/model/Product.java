package com.garageplug.ordermanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_desc")
	private String productDescription;
	@Column(name = "product_price")
	private double productPrice;
	@Column(name = "available_quantity")
	private int availableQuantity;

}
