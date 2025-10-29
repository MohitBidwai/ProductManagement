package com.mohit.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mohit.ProductManagemetSystemApplication;
import com.mohit.dto.ProductRequestDto;
import com.mohit.dto.ProductResponseDto;
import com.mohit.models.Product;
import com.mohit.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

	// private final ProductManagemetSystemApplication
	// productManagemetSystemApplication;

	private final ProductService productService;
//
//    ProductController(ProductManagemetSystemApplication productManagemetSystemApplication) {
//        this.productManagemetSystemApplication = productManagemetSystemApplication;
//    }

	@PostMapping("/save")
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDto productDto) {
		try {
			Boolean savedProduct = productService.saveProduct(productDto);
			if (!savedProduct) {
				return new ResponseEntity<>("Product not saved", HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("saved success", HttpStatus.CREATED);

	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllProducts() {
		List<ProductRequestDto> products = null;
		try {
			products = productService.getALLProducts();
			if (CollectionUtils.isEmpty(products)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductId(@PathVariable(name = "id") Integer id) {

		ProductRequestDto product = null;
		try {
			product = productService.getProductById(id);
			if (ObjectUtils.isEmpty(product)) {
				return new ResponseEntity<>("Product with" + id + "Not found", HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Integer id) {
		Boolean deletedProduct = null;
		try {
			deletedProduct = productService.deleteProduct(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Delete Success", HttpStatus.OK);
	}

	@GetMapping("/pagination")
	public ResponseEntity<?> getPaginatedProducts(@RequestParam int pageNo, @RequestParam int pageSize) {
		ProductResponseDto productsWithPagiantion = null;
		try {

			productsWithPagiantion = productService.getProductsWithPagiantion(pageNo, pageSize, null, null);
			if (ObjectUtils.isEmpty(productsWithPagiantion)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(productsWithPagiantion, HttpStatus.OK);

	}

}
