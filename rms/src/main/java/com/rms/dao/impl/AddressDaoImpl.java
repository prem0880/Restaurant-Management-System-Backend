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
import com.rms.dao.AddressDao;
import com.rms.entity.Address;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class);


	@Override
	public boolean addAddress(Address address) {
		logger.trace("Entering addAddress method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			address.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long id = (Long) session.save(address);
			if (id != 0)
				flag = true;
			session.flush();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.ADDRESS_SAVE_ERROR+e.getMessage());
		}
		return flag;
	}

	@Override
	public List<Address> getAddressByPhoneNumber(Long phoneNumber) {
		logger.trace("Entering getAddressByPhoneNumber method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Address> query = session.createQuery("FROM Address a WHERE a.customer.phoneNumber=:phoneNumber",
					Address.class);
			query.setParameter("phoneNumber", phoneNumber);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}

	}

	@Override
	public Address getAddressByCustomerId(Long customerId) {
		logger.trace("Entering getAddressByCustomerId method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Address> query = session.createQuery("FROM Address a WHERE a.customer.id=:customerId", Address.class);
			query.setParameter("customerId", customerId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Address getAddressById(Long id) {
		logger.trace("Entering getAddressById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Address address = null;
			address = session.get(Address.class, id);
			return address;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR+e.getMessage());
		}
	}

}
