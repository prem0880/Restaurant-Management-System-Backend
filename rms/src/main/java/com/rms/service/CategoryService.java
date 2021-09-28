package com.rms.service;

import java.util.List;

import com.rms.dto.CategoryDto;

public interface CategoryService {

	String deleteCategory(Long id);
	
	String updateCategory(Long id, CategoryDto categoryDto);
	
	String addCategory(CategoryDto categoryDto);
	
	CategoryDto getCategoryById(Long id);
	
	List<CategoryDto> getAllCategory();
	
}
