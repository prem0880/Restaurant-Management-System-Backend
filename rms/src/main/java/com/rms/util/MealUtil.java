package com.rms.util;

import com.rms.dto.MealDto;
import com.rms.entity.Meal;

public class MealUtil {

	public static Meal toEntity(MealDto mealDto) {
		Meal meal = new Meal();
		meal.setName(mealDto.getName());
		return meal;
	}
	
	public static MealDto toDto(Meal meal) {
		MealDto mealDto = new MealDto();
		mealDto.setName(meal.getName());
		return mealDto;
	}
		
}
