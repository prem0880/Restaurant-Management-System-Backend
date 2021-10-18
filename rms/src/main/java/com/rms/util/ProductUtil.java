package com.rms.util;

import com.rms.dto.ProductDto;
import com.rms.entity.Product;

public class ProductUtil {
	
	private ProductUtil() {
		
	}

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
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setImage(product.getImage());
		productDto.setPrice(product.getPrice());
		productDto.setType(product.getType());
		productDto.setDescription(product.getDescription());
		productDto.setTax(product.getTax());
		productDto.setCategory(product.getCategory());
		productDto.setMeal(product.getMeal());
		productDto.setCreatedOn(product.getCreatedOn());
		productDto.setUpdatedOn(product.getUpdatedOn());
		return productDto;
	}

}
