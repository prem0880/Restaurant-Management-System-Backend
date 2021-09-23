package com.rms.service;

import java.util.List;

import com.rms.dto.CategoryDto;
import com.rms.entity.Category;

public interface CategoryService {

	public String deleteCategory(Long id);
	
	public String updateCategory(Long id, CategoryDto categoryDto);
	
	public String addCategory(CategoryDto categoryDto);
	
	public Category getCategoryById(Long id);
	
	public List<Category> getAllCategory();
	
}
