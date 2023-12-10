package com.rms.service;

import java.util.List;

import com.rms.dto.MealDto;

public interface MealService {
	
	/**
	 * 
	 * @param meal id as input
	 * @return  success string message
	 */
	String deleteMeal(Long id);

	/**
	 * 
	 * @param meal id as input
	 * @param meal DTO object
	 * @return	 success string message
	 */
	String updateMeal(Long id, MealDto mealDto);

	/**
	 * 
	 * @param meal DTO object as input
	 * @return  success string message
	 */	
	String addMeal(MealDto mealDto);

	/**
	 * 
	 * @param meal id as input
	 * @return  meal DTO object
	 */
	MealDto getMealById(Long id);

	/**
	 * 
	 * @return  List of meal DTO objects
	 */
	List<MealDto> getAllMeal();
	
	/**
	 * 
	 * @param meal name as input
	 * @return  meal identifier
	 */
	Long getMealByName(String meal);
}
