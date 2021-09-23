package com.rms.util;


import com.rms.dto.CategoryDto;
import com.rms.entity.Category;


public class CategoryMapper {
	
	public static Category toEntity(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		return category;
	}
	
	
}
