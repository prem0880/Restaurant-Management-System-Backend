package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.CategoryDao;
import com.rms.dto.CategoryDto;
import com.rms.entity.Category;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.CategoryService;
import com.rms.util.CategoryUtil;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	
	@Override
	public String deleteCategory(Long id) {
		try{
			return categoryDao.deleteCategory(id);
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateCategory(Long id, CategoryDto categoryDto) {
		try{
		String result=null;
		Category category = CategoryUtil.toEntity(categoryDto);	
		boolean flag=categoryDao.updateCategory(id, category);
		if(flag) {
			result="Category Updation is Successful";
		}
		return result;
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String addCategory(CategoryDto categoryDto) {
		try{
		String result=null;
		Category category = CategoryUtil.toEntity(categoryDto);
		boolean flag=categoryDao.addCategory(category);
		if(flag) {
			result="Category Creation is Successful";
		}
		return result;
		}
		catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		try{
			Category category=categoryDao.getCategoryById(id);
			if(category!=null) {
				return CategoryUtil.toDto(category);
			}
			else {
				throw new BusinessLogicException("No records Found for Category");
			}
		}
		catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		try{
			List<Category> categoryEntity=categoryDao.getAllCategory();
			if(categoryEntity!=null) {
				List<CategoryDto> categoryDto = new ArrayList<>();
				categoryEntity.stream().forEach(entity->categoryDto.add(CategoryUtil.toDto(entity)));
				return categoryDto;
			}else {
				throw new BusinessLogicException("No records Found for Category");
			}
		
		}catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}


	
	
	
}
