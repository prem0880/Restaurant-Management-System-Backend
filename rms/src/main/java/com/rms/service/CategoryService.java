package com.rms.service;

import java.util.List;

import com.rms.dto.CategoryDto;

public interface CategoryService {

	/**
	 * 
	 * @param category id as input
	 * @return  success string message
	 */
	String deleteCategory(Long id);

	/**
	 * 
	 * @param category id as input
	 * @param category DTO object
	 * @return	 success string message
	 */
	String updateCategory(Long id, CategoryDto categoryDto);

	/**
	 * 
	 * @param category DTO object as input
	 * @return  success string message
	 */
	String addCategory(CategoryDto categoryDto);

	/**
	 * 
	 * @param category id as input
	 * @return  category DTO object
	 */
	CategoryDto getCategoryById(Long id);

	/**
	 * 
	 * @return  List of Category DTO objects
	 */
	List<CategoryDto> getAllCategory();

}
