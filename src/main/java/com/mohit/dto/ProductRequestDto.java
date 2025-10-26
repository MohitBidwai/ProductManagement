package com.mohit.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

	
	private Integer  id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	
}
