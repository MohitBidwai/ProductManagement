package com.mohit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mohit.dto.ProductRequestDto;
import com.mohit.models.Product;

@Service
public interface ProductService {

	
	public boolean saveProduct(ProductRequestDto product);
	public List<ProductRequestDto>getALLProducts();
	public ProductRequestDto getProductById(Integer id);
	public Boolean deleteProduct(Integer id);
	
}
