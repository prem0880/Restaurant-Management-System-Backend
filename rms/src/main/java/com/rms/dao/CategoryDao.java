package com.rms.dao;

import java.util.List;

import com.rms.entity.Category;

public interface CategoryDao {

    public String deleteCategory(Long id);
	
	public String updateCategory(Long id, Category category);
	
	public String addCategory(Category category);
	
	public Category getCategoryById(Long id);
	
	public List<Category> getAllCategory();
	
	
}
