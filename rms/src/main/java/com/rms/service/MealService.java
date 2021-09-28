package com.rms.service;

import java.util.List;

import com.rms.dto.MealDto;

public interface MealService {
	String deleteMeal(Long id);
	String updateMeal(Long id, MealDto mealDto);
	String addMeal(MealDto mealDto);
	MealDto getMealById(Long id);
	List<MealDto> getAllMeal();
}
