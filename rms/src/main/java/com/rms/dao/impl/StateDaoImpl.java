package com.rms.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.dao.StateDao;
import com.rms.entity.State;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class StateDaoImpl implements StateDao {

	static final String ID_NOT_FOUND = "State not found with id ";
	static final String DB_FETCH_ERROR = "Error in Fetching Data from Database";
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addState(State state) {
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
			throw new DataBaseException("Error in Creation of State");
		}
	}

	@Override
	public List<State> getStatesByCountry(Long countryId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<State> query = session.createQuery("FROM State s WHERE s.country.id=:countryId", State.class);
			query.setParameter("countryId", countryId);
			return query.list();
		} catch (Exception e) {
			throw new DataBaseException(DB_FETCH_ERROR + ID_NOT_FOUND + countryId);
		}

	}

}
