package com.product.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.app.dto.ProductCategoryResponse;
import com.product.app.entity.ProductCategory;
import com.product.app.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public List<ProductCategoryResponse> getAllProductCategory() {

		List<ProductCategoryResponse> listCategoryResponse = new ArrayList<>();
		List<ProductCategory> listCategory = productCategoryRepository.findAll();
		for (ProductCategory productCategory : listCategory) {
			ProductCategoryResponse response = new ProductCategoryResponse();
			response.setCategoryId(productCategory.getCategoryId());
			response.setCategoryName(productCategory.getCategoryName());

			listCategoryResponse.add(response);

		}
		return listCategoryResponse;
	}

}
