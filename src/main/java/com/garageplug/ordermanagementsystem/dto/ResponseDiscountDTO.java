package com.garageplug.ordermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDiscountDTO {
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private double discount;

}
