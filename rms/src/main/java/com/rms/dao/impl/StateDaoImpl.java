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
import com.rms.util.TimeStamp;

@Repository
@Transactional
public class StateDaoImpl implements StateDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public String addState(State state) {
		Session session=sessionFactory.getCurrentSession();
		String result = null;
		state.setCreatedOn(TimeStamp.getTimeStamp());
		session.save(state);
		result="State added successfully.....";
		session.flush();
		return result;
	}

	@Override
	public List<State> getStatesByCountry(Long countryId) {
		Session session=sessionFactory.getCurrentSession();
		Query<State> query=session.createQuery("FROM State s WHERE s.country.id=:countryId",State.class);
		query.setParameter("countryId", countryId);
		return query.list();
	}

}
