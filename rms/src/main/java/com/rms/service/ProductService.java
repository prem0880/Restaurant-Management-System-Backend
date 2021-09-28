package com.rms.service;

import java.util.List;

import com.rms.dto.ProductDto;

public interface ProductService {
	String deleteProduct(Long id);
	String updateProduct(Long id, ProductDto productDto);
	String addProduct(ProductDto productDto);
	ProductDto getProductById(Long id);
	List<ProductDto> getAllProduct();
	
}
