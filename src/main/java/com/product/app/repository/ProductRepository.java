package com.product.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByCategoryId(int categoryId);

	Product findAllByProductId(int productId);
	
	Product findByProductId(int productId);

}
