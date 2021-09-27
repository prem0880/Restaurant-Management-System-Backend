package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.CountryDao;
import com.rms.entity.Country;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao{

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public String addCountry(Country country) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		session.save(country);
		result="Country added successfully.....";
		session.flush();
		return result;
	}

	@Override
	public List<Country> getAllCountry() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Country",Country.class).getResultList();
	}



}
