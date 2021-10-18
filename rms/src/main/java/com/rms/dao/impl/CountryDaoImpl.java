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
import com.rms.dao.CountryDao;
import com.rms.entity.Country;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);


	@Override
	public boolean addCountry(Country country) {
		logger.info("Entering addCountry method");
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
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.COUNTRY_SAVE_ERROR);
		}
	}

	@Override
	public List<Country> getAllCountry() {
		logger.info("Entering getAllCountry method");
		List<Country> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Country> query = session.createQuery("from Country", Country.class);
			list = query.list();
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public String deleteCountry(Long id) {
		logger.info("Entering deleteCountry method");
		try {
			Session session = sessionFactory.getCurrentSession();
			String result = null;
			Country country = null;
			country = session.load(Country.class, id);
			session.delete(country);
			session.flush();
			result = ApplicationConstants.COUNTRY_DELETE_SUCCESS;
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.COUNTRY_DELETE_ERROR + e.getMessage());
		}
	}

	@Override
	public boolean updateCountry(Long id, Country country) {
		logger.info("Entering updateCountry method");
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
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.COUNTRY_UPDATE_ERROR + e.getMessage());
		}
	}

	@Override
	public Country getCountryById(Long id) {
		logger.info("Entering getCountryById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Country country = null;
			country = session.get(Country.class, id);
			return country;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR + e.getMessage());
		}
	}

}
