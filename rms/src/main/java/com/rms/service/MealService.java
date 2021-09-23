package com.rms.service;

import java.util.List;

import com.rms.dto.MealDto;
import com.rms.entity.Meal;

public interface MealService {

	
	public String deleteMeal(Long id);
	
	public String updateMeal(Long id, MealDto mealDto);
	
	public String addMeal(MealDto mealDto);
	
	public Meal getMealById(Long id);
	
	public List<Meal> getAllMeal();
	
	
}
