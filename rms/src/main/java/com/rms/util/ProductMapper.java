package com.rms.util;

import com.rms.dto.ProductDto;
import com.rms.entity.Product;

public class ProductMapper {

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
	
	
}
