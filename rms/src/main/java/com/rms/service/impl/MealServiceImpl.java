package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.MealDao;
import com.rms.dto.MealDto;
import com.rms.entity.Meal;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.IdNotFoundException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.MealService;
import com.rms.util.MealUtil;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private MealDao mealDao;
	
	private static final Logger logger = LogManager.getLogger(MealServiceImpl.class);


	@Override
	public String deleteMeal(Long id) {
		logger.info("Entering deleteMeal method");
		try {
			if(mealDao.getMealById(id)!=null) {
			return mealDao.deleteMeal(id);}
			else {
				throw new IdNotFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updateMeal(Long id, MealDto mealDto) {
		logger.info("Entering updateMeal method");
		try {
			if(mealDao.getMealById(id)!=null) {
				
			String result = null;
			Meal meal = MealUtil.toEntity(mealDto);
			boolean flag = mealDao.updateMeal(id, meal);
			if (flag) {
				result = ApplicationConstants.MEAL_UPDATE_SUCCESS;
			}
			return result;
			}else {
				throw new IdNotFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String addMeal(MealDto mealDto) {
		logger.info("Entering addMeal method");
		try {
			String result = null;
			Meal meal = MealUtil.toEntity(mealDto);
			boolean flag = mealDao.addMeal(meal);
			if (flag) {
				result = ApplicationConstants.MEAL_SAVE_SUCCESS;
			}
			return result;
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public MealDto getMealById(Long id) {
		logger.info("Entering getMealById method");
		try {
			Meal meal = mealDao.getMealById(id);
			if (meal != null) {
				return MealUtil.toDto(meal);
			}else {
				throw new NoRecordFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<MealDto> getAllMeal() {
		logger.info("Entering getAllMeal method");
		try {
			List<Meal> mealEntity = mealDao.getAllMeal();
			if (CollectionUtils.isEmpty(mealEntity)) {
				throw new NoRecordFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
			else {	List<MealDto> mealDto = new ArrayList<>();
				mealEntity.stream().forEach(entity -> mealDto.add(MealUtil.toDto(entity)));
				return mealDto;
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Long getMealByName(String meal) {
		logger.info("Entering getMealByName method");
		try {
			Meal mealObj = mealDao.getMealByName(meal);
			if (mealObj != null) {
				return mealObj.getId();
			} else {
				throw new NoRecordFoundException(ApplicationConstants.MEAL_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

}
