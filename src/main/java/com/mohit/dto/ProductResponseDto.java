package com.mohit.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

	private List<ProductRequestDto>products;
	private long totalElements;
	private int totalPages;
	private Boolean isFirst;
	private Boolean isLast;
	private int pageNo;
	private int pageSize;
	
	
}
