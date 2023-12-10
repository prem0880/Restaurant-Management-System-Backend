package com.rms.service;

import java.util.List;

import com.rms.dto.ProductDto;

public interface ProductService {
	
	/**
	 * 
	 * @param product id as input
	 * @return  success string message
	 */
	String deleteProduct(Long id);

	/**
	 * 
	 * @param product id as input
	 * @param product DTO object
	 * @return	 success string message
	 */
	String updateProduct(Long id, ProductDto productDto);

	/**
	 * 
	 * @param product DTO object as input
	 * @return  success string message
	 */
	String addProduct(ProductDto productDto);

	/**
	 * 
	 * @param product id as input
	 * @return  product DTO object
	 */
	ProductDto getProductById(Long id);

	/**
	 * 
	 * @return  List of product DTO objects
	 */
	List<ProductDto> getAllProduct();
	
	/**
	 * 
	 * @return  List of product DTO objects
	 */
	List<ProductDto> getAllProductByMeal();

	/**
	 * 
	 * @param category id as input
	 * @param product type string
	 * @return	 List of  product DTO objects
	 */	
	List<ProductDto> getProductByTypeAndCategory(Long categoryId, String type);
	
	/**
	 * 
	 * @param meal id as input
	 * @return List of  product DTO objects
	 */
	List<ProductDto> getProductByMeal(Long mealId);
		

}
