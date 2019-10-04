package com.product.app.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.app.dto.ProductCategoryResponse;
import com.product.app.dto.ProductDetailsResponseDTO;
import com.product.app.dto.ProductResponseDto;
import com.product.app.entity.Product;
import com.product.app.service.ProductCategoryServiceImpl;
import com.product.app.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;
	
	@InjectMocks
	CategoryController categoryController;

	private MockMvc mockMvc;

	@Mock
	ProductCategoryServiceImpl productCategoryServiceImpl;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void getProductCategoriesTest() throws Exception {
		List<ProductCategoryResponse> categoryResponse = new ArrayList<ProductCategoryResponse>();
		ProductCategoryResponse categoryResponse1 = new ProductCategoryResponse();
		categoryResponse1.setCategoryId(100);
		categoryResponse1.setCategoryName("Savings");
		categoryResponse.add(categoryResponse1);

		Mockito.when(productCategoryServiceImpl.getAllProductCategory()).thenReturn(categoryResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/category").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(""))).andReturn();

		ResponseEntity<List<ProductCategoryResponse>> response = categoryController.getProductCategories();

		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testGetAllProducts() throws Exception {

		Product product = new Product();
		product.setProductId(1);

		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setCategoryId(1);
		productResponseDto.setProductId(1);
		productResponseDto.setProductName("ING Insurance");
		List<ProductResponseDto> productsList = new ArrayList<>();
		productsList.add(productResponseDto);

		Mockito.when(productService.getAllProducts(Mockito.anyInt())).thenReturn(productsList);

		mockMvc.perform(MockMvcRequestBuilders.post("/category/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(""))).andReturn();
		ResponseEntity<List<ProductResponseDto>> actual = categoryController.getAllProducts(1);
		assertEquals(1, actual.getBody().size());
		assertEquals(200, actual.getStatusCodeValue());
	}

	@Test
	public void testGetProductDetails() throws Exception {

		Product product = new Product();
		product.setProductId(1);

		ProductDetailsResponseDTO productResponseDto = new ProductDetailsResponseDTO();

		productResponseDto.setProductDescription("Insurance Policy");
		productResponseDto.setProductId(1);

		Mockito.when(productService.getProductDetails(Mockito.anyInt())).thenReturn(productResponseDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/products/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(""))).andReturn();
		ResponseEntity<ProductDetailsResponseDTO> actual = productController.getProductDetails(1);
		assertEquals("Insurance Policy", actual.getBody().getProductDescription());
		assertEquals(1, actual.getBody().getProductId());
		assertEquals(200, actual.getStatusCodeValue());
	}

	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
