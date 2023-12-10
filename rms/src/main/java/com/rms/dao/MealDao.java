package com.rms.dao;

import java.util.List;

import com.rms.entity.Meal;

public interface MealDao {

	/**
	 * 
	 * @param meal id as input
	 * @return  success string message
	 */
	String deleteMeal(Long id);

	/**
	 * 
	 * @param meal id as input
	 * @param meal Entity object
	 * @return	boolean value
	 */
	boolean updateMeal(Long id, Meal meal);

	/**
	 * 
	 * @param meal Entity object as input
	 * @return  boolean value
	 */	
	boolean addMeal(Meal meal);

	/**
	 * 
	 * @param meal id as input
	 * @return  meal Entity object
	 */
	Meal getMealById(Long id);

	/**
	 * 
	 * @return  List of meal Entity objects
	 */
	List<Meal> getAllMeal();

	/**
	 * 
	 * @param meal name as input
	 * @return  meal Entity object
	 */
	Meal getMealByName(String meal);
}
