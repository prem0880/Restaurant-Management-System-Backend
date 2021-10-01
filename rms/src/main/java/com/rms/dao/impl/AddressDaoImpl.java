package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.AddressDao;
import com.rms.entity.Address;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

	static final String ID_NOT_FOUND = "Address not found with id ";
	static final String DB_FETCH_ERROR = "Error in Fetching Data from Database";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addAddress(Address address) {
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			address.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long id = (Long) session.save(address);
			if (id != 0)
				flag = true;
			session.flush();
		} catch (Exception e) {
			throw new DataBaseException("Error in Creation of Address");
		}
		return flag;
	}

	@Override
	public List<Address> getAddressByPhoneNumber(Long phoneNumber) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Address> query = session.createQuery("FROM Address a WHERE a.customer.phoneNumber=:phoneNumber",
					Address.class);
			query.setParameter("phoneNumber", phoneNumber);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException("Error in Fetching Address Data From Database");
		}

	}

	@Override
	public Address getAddressByCustomerId(Long customerId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Address> query = session.createQuery("FROM Address a WHERE a.customer.id=:customerId", Address.class);
			query.setParameter("customerId", customerId);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new DataBaseException("Error in Fetching Address Data From Database");
		}
	}

	@Override
	public Address getAddressById(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Address address = null;
			address = session.get(Address.class, id);
			return address;
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR + ID_NOT_FOUND + id);
		}
	}

}
