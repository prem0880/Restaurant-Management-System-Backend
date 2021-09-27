package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.CountryDao;
import com.rms.entity.Country;
import com.rms.exception.IdNotFoundException;
import com.rms.util.TimeStamp;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao{

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public String addCountry(Country country) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		country.setCreatedOn(TimeStamp.getTimeStamp());
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

	@Override
	public String deleteCountry(Long id) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Country country=null;
		boolean stat=false;
		try {
			country=session.load(Country.class, id);
			if(country.getName()!=null) {
				stat=true;
			}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException("Deletion has failed with id: "+id);
		}
		if(stat)
		{
			session.delete(country);
			session.flush();
			result="Deletion is successful with id: "+id;
		}
		return result;
	}

	@Override
	public String updateCountry(Long id, Country country) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		Country updateCountry=null;
		boolean stat=false;
		try {
			updateCountry=session.load(Country.class, id);
			if(updateCountry.getName()!=null) {
				stat=true;
			}
		}
		catch(org.hibernate.ObjectNotFoundException e)
		{
			throw new IdNotFoundException("Updation has failed with id: "+id);
		}
		if(stat) {
			country.setCreatedOn(updateCountry.getCreatedOn());
			country.setId(id);
			country.setUpdatedOn(TimeStamp.getTimeStamp());
			session.merge(country);
			session.flush();
			result="Country Updation is successful for id: "+id;
		}
		return result;
	}

	@Override
	public Country getCountryById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		Country country =null;
		country=session.get(Country.class, id);
		if(country!=null)
		{
			return country;
			
		}else {
    		throw new IdNotFoundException("Sorry, Category could not be retrived with  " +  id);
    	}
		
	}



}
