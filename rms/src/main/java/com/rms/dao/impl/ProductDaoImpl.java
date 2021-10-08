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
import com.rms.dao.ProductDao;
import com.rms.entity.Product;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);


	@Override
	public String deleteProduct(Product product) {
		logger.info("Entering deleteProduct method");
		try {
			String result = null;
			Session session = sessionFactory.getCurrentSession();
			session.delete(product);
			session.flush();
			result = ApplicationConstants.PRODUCT_DELETE_SUCCESS;
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.PRODUCT_DELETE_ERROR);
		}

	}

	@Override
	public boolean updateProduct(Long id, Product product) {
		logger.info("Entering updateProduct method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Product updateProduct = null;
			updateProduct = session.load(Product.class, id);
			product.setCreatedOn(updateProduct.getCreatedOn());
			product.setId(id);
			product.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object value = session.merge(product);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.PRODUCT_NOT_FOUND+ApplicationConstants.PRODUCT_UPDATE_ERROR);
		}
	}

	@Override
	public boolean addProduct(Product product) {
		logger.info("Entering addProduct method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			product.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(product);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.PRODUCT_SAVE_ERROR);
		}
	}

	@Override
	public Product getProductById(Long id) {
		logger.info("Entering getProductById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Product product = null;
			product = session.get(Product.class, id);
			return product;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+ApplicationConstants.PRODUCT_NOT_FOUND);
		}

	}

	@Override
	public List<Product> getAllProduct() {
		logger.info("Entering getAllProduct method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Product> query = session.createQuery("from Product", Product.class);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public List<Product> getProductByTypeAndCategory(Long categoryId, String type) {
		logger.info("Entering getProductByTypeAndCategory method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Product> query = session
					.createQuery("from Product p where p.category.id=:categoryId AND p.type=:type", Product.class);
			query.setParameter("categoryId", categoryId);
			query.setParameter("type", type);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}

	}

	@Override
	public List<Product> getProductByMeal(Long mealId) {
		logger.info("Entering getProductByMeal method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Product> query = session
					.createQuery("from Product p where p.meal.id=:mealId", Product.class);
			query.setParameter("mealId", mealId);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}

	}

}
