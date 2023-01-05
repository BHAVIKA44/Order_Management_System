package com.garageplug.ordermanagementsystem.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerid;
	@Column(name = "first_name")
	private String cusFirstname;
	@Column(name = "last_name")
	private String cusLastname;
	@Column(name = "email")
	private String cusEmail;
	@Column(name = "category")
	private String cusCategory;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerid")
	private List<Order> orders;

	public Customer(String cusFirstname, String cusLastname, String cusEmail, String cusCategory, List<Order> orders) {
		super();
		this.cusFirstname = cusFirstname;
		this.cusLastname = cusLastname;
		this.cusEmail = cusEmail;
		this.cusCategory = cusCategory;
		this.orders = orders;
	}

}
