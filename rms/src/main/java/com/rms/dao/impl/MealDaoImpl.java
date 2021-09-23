package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.MealDao;
import com.rms.entity.Meal;
import com.rms.exception.IdNotFoundException;


@Repository
@Transactional
public class MealDaoImpl implements MealDao {

	
	static final String ID_NOT_FOUND="Meal not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Meal...";

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public String deleteMeal(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Meal meal=null;
		boolean stat=false;
		try {
			meal=session.load(Meal.class, id);
			if(meal.getName()!=null) {
				stat=true;
			}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException("Deletion has failed. "+ID_NOT_FOUND+id);
		}
		if(stat)
		{
			session.delete(meal);
			session.flush();
			result="Deletion is successful with id: "+id;
		}
		return result;
		
		
	}

	@Override
	public String updateMeal(Long id, Meal meal) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Meal mealEntity=null;
		boolean stat=false;
		try {
				mealEntity=session.load(Meal.class, id);
				if(mealEntity.getName()!=null) {
					stat=true;
				}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+id);
		}
		
		if(stat) {
			mealEntity.setName(meal.getName());
			session.flush();
			result="Meal Updation is successful for id: "+id;
		}
	
		return result;
	}

	@Override
	public String addMeal(Meal meal) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.save(meal);
		result="Meal added successfully.....";
		session.flush();
		return result;
		
		
	}

	@Override
	public Meal getMealById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Meal meal =null;
		meal=session.get(Meal.class, id);
		if(meal!=null)
		{
			return meal;
			
		}else {
    		throw new IdNotFoundException("Sorry, Meal could not be retrived " + ID_NOT_FOUND+ id);
    	}
		
	}

	@Override
	public List<Meal> getAllMeal() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Meal",Meal.class).getResultList();
	}

}
