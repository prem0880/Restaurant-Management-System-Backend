package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.rms.dao.CategoryDao;
import com.rms.entity.Category;
import com.rms.exception.IdNotFoundException;
import com.rms.util.TimeStamp;
                       
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{

	
	static final String ID_NOT_FOUND="Category not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Category...";

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public String deleteCategory(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Category category=null;
		boolean stat=false;
		try {
			category=session.load(Category.class, id);
			if(category.getName()!=null) {
				stat=true;
			}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException("Deletion has failed."+ID_NOT_FOUND+id);
		}
		if(stat)
		{
			session.delete(category);
			session.flush();
			result="Deletion is successful with id: "+id;
		}
		return result;
	}

	@Override
	public String updateCategory(Long id, Category category) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Category categoryEntity=null;
		boolean stat=false;
		try {
				categoryEntity=session.load(Category.class, id);
				if(categoryEntity.getName()!=null) {
					stat=true;
				}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+id);
		}
		
		if(stat) {
			category.setCreatedOn(categoryEntity.getCreatedOn());
			category.setId(id);
			category.setUpdatedOn(TimeStamp.getTimeStamp());
			session.merge(category);
			session.flush();
			result="Category Updation is successful for id: "+id;
		}
	
		return result;
	}

	@Override
	public String addCategory(Category category) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		category.setCreatedOn(TimeStamp.getTimeStamp());
		session.save(category);
		result="Category added successfully.....";
		session.flush();
		return result;
	}

	@Override
	public Category getCategoryById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Category category =null;
		category=session.get(Category.class, id);
		if(category!=null)
		{
			return category;
			
		}else {
    		throw new IdNotFoundException("Sorry, Category could not be retrived " + ID_NOT_FOUND+ id);
    	}
		
	
	}

	@Override
	public List<Category> getAllCategory() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Category",Category.class).getResultList();
	}

		
	
	
}
