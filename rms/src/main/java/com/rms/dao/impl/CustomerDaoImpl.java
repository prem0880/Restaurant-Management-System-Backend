package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.CustomerDao;
import com.rms.entity.Customer;
import com.rms.exception.IdNotFoundException;
import com.rms.util.TimeStamp;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	static final String ID_NOT_FOUND="Customer not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Customer...";

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String addCustomer(Customer customer) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.save(customer);
		customer.setCreatedOn(TimeStamp.getTimeStamp());
		result="Customer added successfully.....";
		session.flush();
		return result;
	}

	@Override
	public List<Customer> getAllCustomer() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Customer",Customer.class).getResultList();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Customer customer =null;
		customer=session.get(Customer.class, id);
		if(customer!=null)
		{
			return customer;
			
		}else {
    		throw new IdNotFoundException("Sorry, Customer could not be retrived " + ID_NOT_FOUND+ id);
    	}
		
	}

	@Override
	public String updateCustomer(Long id, Customer customer) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Customer customerEntity=null;
		boolean stat=false;
		try {
				customerEntity=session.load(Customer.class, id);
				if(customerEntity.getName()!=null) {
					stat=true;
				}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+id);
		}
		
		if(stat) {
			customer.setCreatedOn(customerEntity.getCreatedOn());
			customer.setId(id);
			customer.setUpdatedOn(TimeStamp.getTimeStamp());
			session.merge(customer);
			session.flush();
			result="Customer Updation is successful for id: "+id;
		}
		return result;
	}


}
