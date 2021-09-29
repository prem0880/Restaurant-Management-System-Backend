package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rms.dao.ProductDao;
import com.rms.entity.Product;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;


@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	

	
	static final String ID_NOT_FOUND="Product not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Product...";
	static final String DB_FETCH_ERROR="Error in Fetching Data from Database";
		
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String deleteProduct(Product product) {
		try {
		String result = null;
		Session session=sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
		result="Product Deletion is Successfully!";
		return result;
		}catch (Exception e) {
			throw new DataBaseException("Error in Deletion"+ID_NOT_FOUND);
		}
		
	}

	@Override
	public boolean updateProduct(Long id, Product product) {
		boolean flag=false;
		try {
		Session session=sessionFactory.getCurrentSession();
		Product updateProduct=null;
		updateProduct=session.load(Product.class, id);
		product.setCreatedOn(updateProduct.getCreatedOn());
		product.setId(id);
		product.setUpdatedOn(TimeStampUtil.getTimeStamp());
		Object value=session.merge(product);
		if(value!=null) {
			flag=true;
		}
		session.flush();
		return flag;
		}catch (Exception e) {
			throw new DataBaseException(ID_NOT_FOUND+COULDNT_UPDATE);
		}
	}

	@Override
	public boolean addProduct(Product product) {
		boolean flag=false;
		try{
		Session session=sessionFactory.getCurrentSession();
		product.setCreatedOn(TimeStampUtil.getTimeStamp());
		Long value=(Long)session.save(product);
		if(value!=null) {
			flag=true;
		}
		session.flush();
		return flag;
		}catch (Exception e) {
			throw new DataBaseException("Error in Creation");
		}
	}

	@Override
	public Product getProductById(Long id) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Product product =null;
			product=session.get(Product.class, id);
			return product;
		}catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR+ ID_NOT_FOUND+ id);
		}

	}

	@Override
	public List<Product> getAllProduct() {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query<Product> query=session.createQuery("from Product",Product.class);
			return query.list();
		}catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

	

}
