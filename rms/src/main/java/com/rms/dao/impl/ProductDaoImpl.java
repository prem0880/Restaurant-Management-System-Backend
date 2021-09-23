package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.rms.dao.ProductDao;
import com.rms.entity.Product;
import com.rms.exception.IdNotFoundException;


@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	static final String ID_NOT_FOUND="Product not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Product...";
		
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String deleteProduct(Product product) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.delete(product);
		session.flush();
		result="Product Deletion is Successfully!";
		return result;
		
	}

	@Override
	public String updateProduct(Long id, Product product) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Product updateProduct=null;
		boolean stat=false;
		try {
				updateProduct=session.load(Product.class, id);
				if(updateProduct.getName()!=null) {
					stat=true;
				}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+id);
		}
		
		if(stat) {
			updateProduct.setImage(product.getName());
			updateProduct.setCategory(product.getCategory());
			updateProduct.setDescription(product.getDescription());
			updateProduct.setMeal(product.getMeal());
			updateProduct.setPrice(product.getPrice());
			updateProduct.setTax(product.getTax());
			updateProduct.setType(product.getType());
			session.flush();
			result="Product Updation is successful for id: "+id;
		}
	
		return result;
	}

	@Override
	public String addProduct(Product product) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.save(product);
		session.flush();
		result="Product Added Successfully...";
		return result;
	}

	@Override
	public Product getProductById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Product product =null;
		product=session.get(Product.class, id);
		if(product!=null)
		{
			return product;
			
		}else {
    		throw new IdNotFoundException("Sorry, Product could not be retrived " + ID_NOT_FOUND+ id);
    	}

	}

	@Override
	public List<Product> getAllProduct() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Product",Product.class).getResultList();
	}

	

}
