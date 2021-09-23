package com.rms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.MealDao;
import com.rms.dto.MealDto;
import com.rms.entity.Meal;
import com.rms.service.MealService;
import com.rms.util.MealMapper;


@Service
public class MealServiceImpl implements MealService {

	
	@Autowired
	private MealDao mealDao;
	
	@Override
	public String deleteMeal(Long id) {
		return mealDao.deleteMeal(id);
	}

	@Override
	public String updateMeal(Long id, MealDto mealDto) {
		Meal meal = MealMapper.toEntity(mealDto);
		return mealDao.updateMeal(id, meal);
	}

	@Override
	public String addMeal(MealDto mealDto) {
		Meal meal = MealMapper.toEntity(mealDto);
		return mealDao.addMeal(meal);
	}

	@Override
	public Meal getMealById(Long id) {
		return mealDao.getMealById(id);
	}

	@Override
	public List<Meal> getAllMeal() {
		return mealDao.getAllMeal();
	}

}
