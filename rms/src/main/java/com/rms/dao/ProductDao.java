package com.rms.dao;

import java.util.List;

import com.rms.entity.Product;

public interface ProductDao {

	/**
	 * 
	 * @param product Entity object
	 * @return  success string message
	 */
	String deleteProduct(Product product);

	/**
	 * 
	 * @param product id as input
	 * @param product Entity object
	 * @return boolean value
	 */
	boolean updateProduct(Long id, Product product);

	/**
	 * 
	 * @param product Entity object as input
	 * @return  boolean value
	 */
	boolean addProduct(Product product);

	/**
	 * 
	 * @param product id as input
	 * @return  product Entity object
	 */
	Product getProductById(Long id);

	/**
	 * 
	 * @param category id as input
	 * @param product type string
	 * @return	 List of  product Entity objects
	 */	
	List<Product> getProductByTypeAndCategory(Long categoryId, String type);

	/**
	 * 
	 * @return  List of product Entity objects
	 */
	List<Product> getAllProduct();
	
	/**
	 * 
	 * @param meal id as input
	 * @return List of product Entity objects
	 */
	List<Product> getProductByMeal(Long mealId);

}
