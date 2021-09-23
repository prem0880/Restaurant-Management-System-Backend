package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CategoryDao;
import com.rms.dto.CategoryDto;
import com.rms.entity.Category;
import com.rms.service.CategoryService;
import com.rms.util.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	
	@Override
	public String deleteCategory(Long id) {
		
		return categoryDao.deleteCategory(id);

	}

	@Override
	public String updateCategory(Long id, CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);	
		return categoryDao.updateCategory(id, category);
	}

	@Override
	public String addCategory(CategoryDto categoryDto) {
		Category category = CategoryMapper.toEntity(categoryDto);
		return categoryDao.addCategory(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();
	}

	
	
	
}
