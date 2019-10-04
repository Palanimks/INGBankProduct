package com.product.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.app.dto.ProductCategoryResponse;
import com.product.app.dto.ProductResponseDto;
import com.product.app.service.ProductCategoryService;
import com.product.app.service.ProductService;

@RestController
@RequestMapping("/ing")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class CategoryController {

	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductService productService;

	
	@GetMapping("/category")
	public ResponseEntity<List<ProductCategoryResponse>> getProductCategories() {
		List<ProductCategoryResponse> response = productCategoryService.getAllProductCategory();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(@PathVariable int categoryId) {
		List<ProductResponseDto> productsList = productService.getAllProducts(categoryId);
		return new ResponseEntity<>(productsList, HttpStatus.OK);

	}
}
