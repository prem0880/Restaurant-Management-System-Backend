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


	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addAddress(Address address) {
		boolean flag=false;
		try {
			Session session=sessionFactory.getCurrentSession();
			address.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long id=(Long)session.save(address);
			if(id!=0)
				flag=true;
			session.flush();
		}catch (Exception e) {
			throw new DataBaseException("Error in Creation of Address");
		}
		return flag;
	}

	@Override
	public List<Address> getAddressByPhoneNumber(Long phoneNumber) {
		try {
		Session session=sessionFactory.getCurrentSession();
		Query<Address> query=session.createQuery("FROM Address a WHERE a.customer.phoneNumber=:phoneNumber",Address.class);
		query.setParameter("phoneNumber", phoneNumber);
		return query.list();
		}catch (Exception e) {
			throw new DataBaseException("Error in Fetching Address Data From Database");
		}
		
	}

}
