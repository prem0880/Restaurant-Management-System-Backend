package com.rms.dao;

import java.util.List;

import com.rms.entity.Meal;

public interface MealDao {

	public String deleteMeal(Long id);
	
	public String updateMeal(Long id, Meal meal);
	
	public String addMeal(Meal meal);
	
	public Meal getMealById(Long id);
	
	public List<Meal> getAllMeal();
	
}
