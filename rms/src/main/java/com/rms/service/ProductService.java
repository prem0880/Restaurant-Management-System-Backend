package com.rms.service;

import java.util.List;

import com.rms.dto.ProductDto;
import com.rms.entity.Product;

public interface ProductService {

	
	public String deleteProduct(Long id,Long categoryId,Long mealId);

	public String updateProduct(Long id, ProductDto productDto);

	public String addProduct(ProductDto productDto);

	public Product getProductById(Long id);

	public List<Product> getAllProduct();
	
}
