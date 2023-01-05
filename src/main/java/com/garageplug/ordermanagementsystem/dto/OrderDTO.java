package com.garageplug.ordermanagementsystem.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private String customerEmail;
	private String customerFirstName;
	private String customerLastName;
	private List<OrderItemDTO> orderItems;

}
