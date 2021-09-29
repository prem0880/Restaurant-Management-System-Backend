package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.rms.dao.CategoryDao;
import com.rms.entity.Category;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;
                       
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{
	

	static final String ID_NOT_FOUND="Category not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Category";
	static final String DB_FETCH_ERROR="Error in Fetching Data from Database";

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String deleteCategory(Long id) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Category category=null;
			String result=null;
			category=session.load(Category.class, id);
			session.delete(category);
			session.flush();
			result="Deletion is successful with id: "+id;
			return result;
		}catch (Exception e) {
			throw new DataBaseException("Error in Deletion"+ID_NOT_FOUND);
		}
		
	}

	@Override
	public boolean updateCategory(Long id, Category category) {
		boolean flag=false;
		try {
			Session session=sessionFactory.getCurrentSession();
			Category categoryObj=null;
			categoryObj=session.load(Category.class, id);
			category.setCreatedOn(categoryObj.getCreatedOn());
			category.setId(id);
			category.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object obj=session.merge(category);
			if(obj!=null) {
				flag=true;
			}
			session.flush();
			return flag;
		}
		catch (Exception e) {
			throw new DataBaseException(ID_NOT_FOUND+COULDNT_UPDATE);
		}
		
	}

	@Override
	public boolean addCategory(Category category) {
		boolean flag=false;
		try{
			Session session=sessionFactory.getCurrentSession();
			category.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value=(Long)session.save(category);
			if(value!=null) {
				flag=true;
			}
			session.flush();
			return flag;
		}catch (Exception e) {
			
			throw new DataBaseException("Error in Creation Of Category");
		}
	
	}

	@Override
	public Category getCategoryById(Long id) {
		try{
		Session session=sessionFactory.getCurrentSession();
		Category category=null;
		category=session.get(Category.class, id);
		return category;
		}catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR+ ID_NOT_FOUND+ id);
		}
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> list=null;
		try {
		Session session=sessionFactory.getCurrentSession();
		Query<Category> query=session.createQuery("from Category",Category.class);
		list=query.list();
		return list;
		}
		catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

		
	
	
}
