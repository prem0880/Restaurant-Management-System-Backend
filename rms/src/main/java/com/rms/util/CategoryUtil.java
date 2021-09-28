package com.rms.util;


import com.rms.dto.CategoryDto;
import com.rms.entity.Category;


public class CategoryUtil {
	
	public static Category toEntity(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		return category;
	}
	
	public static CategoryDto toDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(category.getName());
		return categoryDto; 
	}
	
	
}
