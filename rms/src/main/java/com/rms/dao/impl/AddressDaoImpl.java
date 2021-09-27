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

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String addAddress(Address address) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.save(address);
		result="Address added successfully.....";
		session.flush();
		return result;
	}

	@Override
	public List<Address> getAddressByPhoneNumber(Long phoneNumber) {
		Session session=sessionFactory.getCurrentSession();
		Query<Address> query=session.createQuery("FROM Address a WHERE a.customer.phoneNumber=:phoneNumber",Address.class);
		query.setParameter("phoneNumber", phoneNumber);
		return query.list();
	}

}
