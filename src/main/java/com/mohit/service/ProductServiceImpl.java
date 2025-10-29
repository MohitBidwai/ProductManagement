package com.mohit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.mohit.Repository.ProductRepository;
import com.mohit.dto.ProductRequestDto;
import com.mohit.dto.ProductResponseDto;
import com.mohit.models.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean saveProduct(ProductRequestDto productDto) {
		// TODO Auto-generated method stub
//		Product product = new Product();
//		product.setId(productDto.getId());
//		product.setName(productDto.getName());
//		product.setPrice(productDto.getPrice());
//		product.setDescription(product.getDescription());
//		product.setQuantity(product.getQuantity());
//		

		// Product savedProduct=productRepository.save(product);
		// We converted the dto to entity class
		Product product = mapper.map(productDto, Product.class);
		if (ObjectUtils.isEmpty(product)) {
			return false;
		}
		productRepository.save(product);

		return true;
	}

	@Override
	public List<ProductRequestDto> getALLProducts() {
		// TODO Auto-generated method stub
		List<Product> productList = productRepository.findAll();
		List<ProductRequestDto> productDtoList = new ArrayList<>();

		for (Product products : productList) {
			ProductRequestDto dto = mapper.map(products, ProductRequestDto.class);
			productDtoList.add(dto);

		}

		return productDtoList;

	}

	@Override
	public ProductRequestDto getProductById(Integer id) {
		// TODO Auto-generated method stub

		Optional<Product> findByProduct = productRepository.findById(id);
		if (findByProduct.isPresent()) {
			Product product = findByProduct.get();
			ProductRequestDto dto = mapper.map(product, ProductRequestDto.class);
			return dto;
		}

		return null;

	}

	@Override
	public Boolean deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		Optional<Product> existingProduct = productRepository.findById(id);
		if (existingProduct.isPresent()) {
			productRepository.deleteById(id);
			return true;
		}
		return null;
	}

	@Override
	public ProductResponseDto getProductsWithPagiantion(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Product> page = productRepository.findAll(pageable);
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Product> products = page.getContent();
		List<ProductRequestDto> productDtos = products.stream().map(prod->mapper.map(prod,ProductRequestDto.class)).toList();
		boolean first = page.isFirst();
		boolean last = page.isLast();
		
//		ProductResponseDto productResponse = new ProductResponseDto();
//		productResponse.setProducts(products); these are olde methods 
		
		// We will use Builder methods
		
		ProductResponseDto productResponseDto = ProductResponseDto.builder() .products(productDtos).totalElements(totalElements).pageNo(pageNo).pageSize(pageSize).totalPages(totalPages).isFirst(first).isLast(last).build();
		return productResponseDto;
		
		

	}

}
