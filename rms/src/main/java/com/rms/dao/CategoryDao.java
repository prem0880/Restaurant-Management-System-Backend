package com.rms.dao;

import java.util.List;

import com.rms.entity.Category;

public interface CategoryDao {

	/**
	 * 
	 * @param category id as input
	 * @return  success string message
	 */
	String deleteCategory(Long id);

	/**
	 * 
	 * @param category id as input
	 * @param category Entity object
	 * @return boolean value
	 */
	boolean updateCategory(Long id, Category category);

	/**
	 * 
	 * @param category Entity object as input
	 * @return  boolean value
	 */
	boolean addCategory(Category category);

	/**
	 * 
	 * @param category id as input
	 * @return  category Entity object
	 */
	Category getCategoryById(Long id);

	/**
	 * 
	 * @return  List of Category Entity objects
	 */
	List<Category> getAllCategory();

}
