package com.rms.dao.impl;


import javax.persistence.NoResultException;
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
import com.rms.util.PasswordEncryptionUtil;
import com.rms.util.TimeStampUtil;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(LoginDaoImpl.class);
	
	
	@Override
	public boolean saveLogin(Login login) {
		logger.info("Entering saveLogin method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			login.setCreatedOn(TimeStampUtil.getTimeStamp());
			login.setPassword(PasswordEncryptionUtil.getPassword(login.getPassword()));
			Long id = (Long) session.save(login);
			if (id != 0)
				flag = true;
			session.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.LOGIN_SAVE_ERROR);
		}
		return flag;
	
	}

	@Override
	public boolean updateLogin(String email, String password) {
		logger.info("Entering updateLogin method");
		boolean flag = false;
		try {
			Session session = sessionFactory.getCurrentSession();
			Login loginObj = null;
			loginObj = getLoginByMail(email);
			loginObj.setUpdatedOn(TimeStampUtil.getTimeStamp());
			loginObj.setPassword(PasswordEncryptionUtil.getPassword(password));
			Object obj = session.merge(loginObj);
			if (obj != null) {
				flag = true;
			}
			session.flush();
			return flag;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.LOGIN_NOT_FOUND+ ApplicationConstants.LOGIN_UPDATE_ERROR);
		}
	}

	@Override
	public Login getByEmail(String email) {

		logger.info("Entering getByEmail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Login> query=session.createQuery("FROM Login l where l.email=:email",Login.class);
			query.setParameter("email",email);
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public String getRoleById(Long id) {
		logger.info("Entering getRoleById method");
		try {
			Session session = sessionFactory.getCurrentSession();
			return (String) session.createQuery("SELECT l.role FROM Login l where l.id=:id").setParameter("id", id).getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}

	@Override
	public Login getLoginByMail(String email) {
		logger.info("Entering getLoginByMail method");
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Login> query=session.createQuery("FROM Login l where l.email=:email",Login.class);
			query.setParameter("email",email);
			return query.getSingleResult();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new DataBaseException(ApplicationConstants.DB_FETCH_ERROR);
		}
	}


}
