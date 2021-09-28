package com.rms.dao;

import java.util.List;

import com.rms.entity.Meal;

public interface MealDao {

	 String deleteMeal(Long id);
	
	 boolean updateMeal(Long id, Meal meal);
	
	 boolean addMeal(Meal meal);
	
	 Meal getMealById(Long id);
	
	 List<Meal> getAllMeal();
	
}
