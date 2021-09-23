package com.rms.util;

import com.rms.dto.MealDto;
import com.rms.entity.Meal;

public class MealMapper {

	public static Meal toEntity(MealDto mealDto) {
		Meal meal = new Meal();
		meal.setName(mealDto.getName());
		return meal;
	}
	
	
}
