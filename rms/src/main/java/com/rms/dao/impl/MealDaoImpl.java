package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.MealDao;
import com.rms.entity.Meal;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class MealDaoImpl implements MealDao {

	static final String ID_NOT_FOUND = "Meal not found with id ";
	static final String COULDNT_UPDATE = "Couldn't update Meal...";
	static final String DB_FETCH_ERROR = "Error in Fetching Data from Database";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String deleteMeal(Long id) {
		try {
			String result = null;
			Meal meal = null;
			Session session = sessionFactory.getCurrentSession();
			meal = session.load(Meal.class, id);
			session.delete(meal);
			session.flush();
			result = "Deletion is successful with id: " + id;
			return result;
		} catch (Exception e) {
			throw new DataBaseException("Error in Deletion" + ID_NOT_FOUND);
		}
	}

	@Override
	public boolean updateMeal(Long id, Meal meal) {
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
			throw new DataBaseException(ID_NOT_FOUND + COULDNT_UPDATE);
		}
	}

	@Override
	public boolean addMeal(Meal meal) {
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
			throw new DataBaseException("Error in Creation");
		}
	}

	@Override
	public Meal getMealById(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Meal meal = null;

			meal = session.get(Meal.class, id);
			return meal;
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR + ID_NOT_FOUND + id);
		}

	}

	@Override
	public List<Meal> getAllMeal() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Meal> query = session.createQuery("from Meal", Meal.class);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

}
