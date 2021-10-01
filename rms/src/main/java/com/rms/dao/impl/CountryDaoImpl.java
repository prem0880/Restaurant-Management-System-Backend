package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.CountryDao;
import com.rms.entity.Country;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

	static final String ID_NOT_FOUND = "Country not found with id ";
	static final String DB_FETCH_ERROR = "Error in Fetching Data from Database";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addCountry(Country country) {
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			country.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(country);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			throw new DataBaseException("Error in Creation");
		}
	}

	@Override
	public List<Country> getAllCountry() {
		List<Country> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Country> query = session.createQuery("from Country", Country.class);
			list = query.list();
			return list;
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR);
		}
	}

	@Override
	public String deleteCountry(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String result = null;
			Country country = null;
			country = session.load(Country.class, id);
			session.delete(country);
			session.flush();
			result = "Deletion is successful with id: " + id;
			return result;
		} catch (Exception e) {
			throw new DataBaseException("Error in Deletion" + ID_NOT_FOUND);
		}
	}

	@Override
	public boolean updateCountry(Long id, Country country) {
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Country updateCountry = null;
			updateCountry = session.load(Country.class, id);
			country.setCreatedOn(updateCountry.getCreatedOn());
			country.setId(id);
			country.setUpdatedOn(TimeStampUtil.getTimeStamp());
			Object obj = session.merge(country);
			if (obj != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			throw new DataBaseException("Error in Updation" + ID_NOT_FOUND);
		}
	}

	@Override
	public Country getCountryById(Long id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Country country = null;
			country = session.get(Country.class, id);
			return country;
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR + ID_NOT_FOUND);
		}
	}

}
