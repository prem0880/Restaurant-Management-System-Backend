package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.MealDao;
import com.rms.entity.Meal;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class MealDaoImpl implements MealDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(MealDaoImpl.class);


	@Override
	public String deleteMeal(Long id) {
		logger.info("Entering deleteMeal method");
		try {
			String result = null;
			Meal meal = null;
			Session session = sessionFactory.getCurrentSession();
			meal = session.load(Meal.class, id);
			session.delete(meal);
			session.flush();
			result = ApplicationConstants.MEAL_DELETE_SUCCESS;
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.MEAL_NOT_FOUND+ApplicationConstants.MEAL_DELETE_ERROR);
		}
	}

	@Override
	public boolean updateMeal(Long id, Meal meal) {
		logger.info("Entering updateMeal method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Meal mealEntity = null;
			mealEntity = session.load(Meal.class, id);
			meal.setCreatedOn(mealEntity.getCreatedOn());
			meal.setId(id);
			meal.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object value = session.merge(meal);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.MEAL_NOT_FOUND+ ApplicationConstants.MEAL_UPDATE_ERROR);
		}
	}

	@Override
	public boolean addMeal(Meal meal) {
		logger.info("Entering addMeal method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			meal.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(meal);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.MEAL_SAVE_ERROR);
		}
	}

	@Override
	public Meal getMealById(Long id) {
		logger.info("Entering getMealById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Meal meal = null;
			meal = session.get(Meal.class, id);
			return meal;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR + e.getMessage());
		}

	}

	@Override
	public List<Meal> getAllMeal() {
		logger.info("Entering getAllMeal method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Meal> query = session.createQuery("from Meal", Meal.class);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Meal getMealByName(String meal) {
		logger.info("Entering getMealByName method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Meal> query = session.createQuery("from Meal m where m.name=:meal", Meal.class);
			query.setParameter("meal", meal);
			return query.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

}
