package com.mohit.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequestDto {

	
	private Integer  id;
	@NotBlank
	private String name;
	@NotEmpty
	@Size(min = 3 , max = 10 , message ="description should be min 3 size and max 10 size")
	private String description;
	@NotNull(message = "price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0")
    @Digits(integer = 10, fraction = 2, message = "must have up to 2 decimal places")
	
	private Double price;
	private Integer quantity;
	
}
