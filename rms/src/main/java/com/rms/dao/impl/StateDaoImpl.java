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
import com.rms.dao.StateDao;
import com.rms.entity.State;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class StateDaoImpl implements StateDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LogManager.getLogger(StateDaoImpl.class);

	@Override
	public boolean addState(State state) {
		logger.info("Entering addState method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			state.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long value = (Long) session.save(state);
			if (value != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.STATE_SAVE_ERROR);
		}
	}

	@Override
	public List<State> getStatesByCountry(Long countryId) {
		logger.info("Entering getStatesByCountry method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<State> query = session.createQuery("FROM State s WHERE s.country.id=:countryId", State.class);
			query.setParameter("countryId", countryId);
			return query.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.STATE_NOT_FOUND +countryId);
		}

	}

}
