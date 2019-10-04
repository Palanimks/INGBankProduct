package com.product.app.service;

import java.util.List;

import com.product.app.dto.ProductDetailsResponseDTO;
import com.product.app.dto.ProductResponseDto;

public interface ProductService {

	List<ProductResponseDto> getAllProducts(int categoryId);

	ProductDetailsResponseDTO getProductDetails(int productId);

}
