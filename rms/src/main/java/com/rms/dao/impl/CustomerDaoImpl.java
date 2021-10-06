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
import com.rms.dao.CustomerDao;
import com.rms.entity.Customer;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);


	@Override
	public Long addCustomer(Customer customer) {
		logger.debug("Entering addCustomer method");
		try {
			Session session = sessionFactory.getCurrentSession();
			customer.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(customer);
			session.flush();
			return value;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.COUNTRY_SAVE_ERROR);
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		logger.debug("Entering getAllCustomer method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery("from Customer", Customer.class);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Customer getCustomerById(Long id) {
		logger.debug("Entering getCustomerById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Customer customer = null;
			customer = session.get(Customer.class, id);
			return customer;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR + e.getMessage());
		}

	}

	@Override
	public boolean updateCustomer(Long id, Customer customer) {
		logger.debug("Entering updateCustomer method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Customer customerEntity = null;
			customerEntity = session.load(Customer.class, id);
			customer.setCreatedOn(customerEntity.getCreatedOn());
			customer.setId(id);
			customer.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object value = session.merge(customer);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.CUSTOMER_UPDATE_ERROR);
		}

	}

	@Override
	public Customer getCustomerByEmail(Customer customer) {
		logger.debug("Entering getCustomerByEmail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Customer> query = session.createQuery("FROM Customer c where c.email=:email", Customer.class);
			query.setParameter("email", customer.getEmail());
			Customer customerObj = null;
			customerObj = query.getSingleResult();
			return customerObj;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

}
