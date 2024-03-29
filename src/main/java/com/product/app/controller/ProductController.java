package com.product.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.app.dto.ProductDetailsResponseDTO;
import com.product.app.service.ProductCategoryService;
import com.product.app.service.ProductService;


@RequestMapping("/ing")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class ProductController {



	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductService productService;
	
	@GetMapping("/prodcuts/{productId}")
	public ResponseEntity<ProductDetailsResponseDTO> getProductDetails(@PathVariable int productId) {
		ProductDetailsResponseDTO productDetails = productService.getProductDetails(productId);
		return new ResponseEntity<>(productDetails, HttpStatus.OK);

	}
	
	
}
