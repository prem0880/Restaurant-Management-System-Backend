package com.rms.dao.impl;


import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.LoginDao;
import com.rms.entity.Login;
import com.rms.exception.DataBaseException;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(LoginDaoImpl.class);
	
	
	@Override
	public boolean saveLogin(Login login) {
		logger.trace("Entering save method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			login.setCreatedOn(TimeStampUtil.getTimeStamp());
			Long id = (Long) session.save(login);
			if (id != 0)
				flag = true;
			session.flush();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.LOGIN_SAVE_ERROR);
		}
		return flag;
	
	}

	@Override
	public boolean updateLogin(String email, String password) {
		logger.trace("Entering updateLogin method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Login loginObj = null;
			loginObj = session.load(Login.class, email);
			loginObj.setUpdatedOn(TimeStampUtil.getTimeStamp());
			loginObj.setEmailId(email);
			loginObj.setPassword(password);
			Object obj = session.merge(loginObj);
			if (obj != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.LOGIN_NOT_FOUND+ ApplicationConstants.LOGIN_UPDATE_ERROR);
		}
	}

	@Override
	public Login getByEmail(String email) {

		logger.trace("Entering getByEmail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Login> query = session.createQuery("from Login l where l.emailId=:email", Login.class);
			query.setParameter("email",email);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
		
	}

}
