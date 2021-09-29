package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.CustomerDao;
import com.rms.entity.Customer;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	
	static final String ID_NOT_FOUND="Customer not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Customer...";
	static final String DB_FETCH_ERROR="Error in Fetching Data from Database";

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addCustomer(Customer customer) {
		boolean flag=false;
		try {
		Session session=sessionFactory.getCurrentSession();
		customer.setCreatedOn(TimeStampUtil.getTimeStamp());
		Long value=(Long)session.save(customer);
		if(value!=null) {
			flag=true;
		}
		session.flush();
		return flag;
		}catch (Exception e) {
			throw new DataBaseException("Error in Creation of Customer");
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		try {
		Session session=sessionFactory.getCurrentSession();
		Query<Customer> query=session.createQuery("from Customer",Customer.class);
		return query.list();
		}catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

	@Override
	public Customer getCustomerById(Long id) {
		try{Session session=sessionFactory.getCurrentSession();
		Customer customer =null;
		customer=session.get(Customer.class, id);
		return customer;
		}catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR+ ID_NOT_FOUND+ id);
		}
		
	}

	@Override
	public boolean updateCustomer(Long id, Customer customer) {
		boolean flag=false;
		try{
			Session session=sessionFactory.getCurrentSession();
			Customer customerEntity=null;
			customerEntity=session.load(Customer.class, id);
			customer.setCreatedOn(customerEntity.getCreatedOn());
			customer.setId(id);
			customer.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object value=session.merge(customer);
			if(value!=null) {
				flag=true;
			}
			session.flush();
			return flag;
		}catch (Exception e) {
			throw new DataBaseException(ID_NOT_FOUND+COULDNT_UPDATE);
		}

	}


}
