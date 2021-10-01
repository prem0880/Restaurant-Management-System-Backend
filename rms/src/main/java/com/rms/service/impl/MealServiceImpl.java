package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.MealDao;
import com.rms.dto.MealDto;
import com.rms.entity.Meal;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.MealService;
import com.rms.util.MealUtil;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;

	@Override
	public String deleteMeal(Long id) {
		try {
			return mealDao.deleteMeal(id);
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateMeal(Long id, MealDto mealDto) {
		try {
			String result = null;
			Meal meal = MealUtil.toEntity(mealDto);
			boolean flag = mealDao.updateMeal(id, meal);
			if (flag) {
				result = "Meal Updation is Successful";
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String addMeal(MealDto mealDto) {
		try {
			String result = null;
			Meal meal = MealUtil.toEntity(mealDto);
			boolean flag = mealDao.addMeal(meal);
			if (flag) {
				result = "Meal Creation is Successful";
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public MealDto getMealById(Long id) {
		try {
			Meal meal = mealDao.getMealById(id);
			if (meal != null) {
				return MealUtil.toDto(meal);
			} else {
				throw new BusinessLogicException("No records Found for Meal");
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<MealDto> getAllMeal() {
		try {
			List<Meal> mealEntity = mealDao.getAllMeal();
			if (mealEntity != null) {
				List<MealDto> mealDto = new ArrayList<>();
				mealEntity.stream().forEach(entity -> mealDto.add(MealUtil.toDto(entity)));
				return mealDto;
			} else {
				throw new BusinessLogicException("No records Found for Meal");
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
