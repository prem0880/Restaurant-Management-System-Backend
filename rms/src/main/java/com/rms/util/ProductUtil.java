package com.rms.util;

import com.rms.dto.ProductDto;
import com.rms.entity.Product;

public class ProductUtil {

	public static Product toEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setImage(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setDescription(productDto.getDescription());
		product.setTax(productDto.getTax());
		product.setType(productDto.getType());
		product.setCategory(productDto.getCategory());
		product.setMeal(productDto.getMeal());
		return product;	
	}
	
	public static ProductDto toDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setName(product.getName());
		productDto.setImage(product.getImage());
		productDto.setPrice(product.getPrice());
		productDto.setDescription(product.getDescription());
		productDto.setTax(product.getTax());
		productDto.setType(product.getType());
		productDto.setCategory(product.getCategory());
		productDto.setMeal(product.getMeal());
		return productDto;	
	}
	
	
}
