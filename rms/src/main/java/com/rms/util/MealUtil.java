package com.rms.util;

import com.rms.dto.MealDto;
import com.rms.entity.Meal;

public class MealUtil {
	
	private MealUtil() {
		
	}

	public static Meal toEntity(MealDto mealDto) {
		Meal meal = new Meal();
		meal.setName(mealDto.getName());
		return meal;
	}

	public static MealDto toDto(Meal meal) {
		MealDto mealDto = new MealDto();
		mealDto.setId(meal.getId());
		mealDto.setName(meal.getName());
		mealDto.setCreatedOn(meal.getCreatedOn());
		mealDto.setUpdatedOn(meal.getUpdatedOn());
		return mealDto;
	}

}
