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
import com.rms.dao.CategoryDao;
import com.rms.entity.Category;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {


	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);


	@Override
	public String deleteCategory(Long id) {
		logger.info("Entering deleteCategory method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Category category = null;
			String result = null;
			category = session.load(Category.class, id);
			session.delete(category);
			session.flush();
			result = ApplicationConstants.CATEGORY_DELETE_SUCCESS;
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.COULDNT_DELETE+e.getMessage());
		}

	}

	@Override
	public boolean updateCategory(Long id, Category category) {
		logger.info("Entering updateCategory method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Category categoryObj = null;
			categoryObj = session.load(Category.class, id);
			category.setCreatedOn(categoryObj.getCreatedOn());
			category.setId(id);
			category.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object obj = session.merge(category);
			if (obj != null) { 
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.CATEGORY_NOT_FOUND + ApplicationConstants.COULDNT_UPDATE);
		}

	}

	@Override
	public boolean addCategory(Category category) {
		logger.info("Entering addCategory method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			category.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(category);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.CATEGORY_SAVE_ERROR);
		}

	}

	@Override
	public Category getCategoryById(Long id) {
		logger.info("Entering getCategoryById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Category category = null;
			category = session.get(Category.class, id);
			return category;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+ ApplicationConstants.CATEGORY_NOT_FOUND + e.getMessage());
		}
	}

	@Override
	public List<Category> getAllCategory() {
		logger.info("Entering getAllCategory method");
		List<Category> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Category> query = session.createQuery("from Category", Category.class);
			list = query.list();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

}
