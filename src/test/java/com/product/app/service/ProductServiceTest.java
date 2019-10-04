package com.product.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.product.app.dto.ProductCategoryResponse;
import com.product.app.dto.ProductDetailsResponseDTO;
import com.product.app.dto.ProductResponseDto;
import com.product.app.entity.Product;
import com.product.app.entity.ProductCategory;
import com.product.app.repository.ProductCategoryRepository;
import com.product.app.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	@Mock
	ProductCategoryRepository productCategoryRepository;
	
	@InjectMocks
	ProductCategoryServiceImpl productCategoryServiceImpl;

	
	@Test
	public void getAllProductCategoryTest()
	{
		List<ProductCategory> listcategory = new ArrayList<ProductCategory>();
		ProductCategory category  =  new ProductCategory();
		category.setCategoryId(100);
		category.setCategoryName("Savings");
		listcategory.add(category);
		
		Mockito.when(productCategoryRepository.findAll()).thenReturn(listcategory);
		
		List<ProductCategoryResponse> response = productCategoryServiceImpl.getAllProductCategory();
		
		assertEquals(100, response.get(0).getCategoryId());
		
		
	}

	@Test
	public void testGetAllProducts() {

		Product product = new Product();
		product.setProductId(1);

		List<Product> productsList = new ArrayList<>();
		productsList.add(product);

		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setCategoryId(1);
		productResponseDto.setProductId(1);
		productResponseDto.setProductName("ING Insurance");
		List<ProductResponseDto> productList = new ArrayList<>();

		productList.add(productResponseDto);
		Mockito.when(productServiceImpl.getAllProducts(Mockito.anyInt())).thenReturn(productList);
		when(productRepository.findAllByCategoryId(Mockito.anyInt())).thenReturn(productsList);
		List<ProductResponseDto> actual = productServiceImpl.getAllProducts(1);
		assertEquals(1, actual.get(0).getCategoryId());
		assertEquals(1, actual.get(0).getProductId());

	}

	@Test
	public void testGetProductDetails() {

		Product product = new Product();
		product.setProductId(1);

	

		ProductDetailsResponseDTO productResponseDto = new ProductDetailsResponseDTO();

		
		productResponseDto.setProductDescription("ING providing life insurance");
		productResponseDto.setProductId(1);
	
		when(productRepository.findAllByProductId(Mockito.anyInt())).thenReturn(product);
		ProductDetailsResponseDTO actual = productServiceImpl.getProductDetails(1);
		assertEquals(1, actual.getProductId());
	
	

	}
}
