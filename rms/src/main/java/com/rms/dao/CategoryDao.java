package com.rms.dao;

import java.util.List;

import com.rms.entity.Category;

public interface CategoryDao {

	String deleteCategory(Long id);

	boolean updateCategory(Long id, Category category);

	boolean addCategory(Category category);

	Category getCategoryById(Long id);

	List<Category> getAllCategory();

}
