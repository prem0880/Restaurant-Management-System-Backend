package com.rms.service.impl;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.LoginDao;
import com.rms.dto.LoginDto;
import com.rms.entity.Login;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.exception.DuplicateIdException;
import com.rms.exception.NoRecordFoundException;
import com.rms.service.LoginService;
import com.rms.util.LoginUtil;
import com.rms.util.PasswordEncryptionUtil;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
		
	private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);


	@Override
	public String saveLogin(LoginDto loginDto) {
		logger.info("Entering saveLogin method");
		try {
			Login entity = LoginUtil.toEntity(loginDto);
			Login loginCheck = null;
			loginCheck = loginDao.getByEmail(entity.getEmail());
			if(loginCheck == null) {
				boolean result = loginDao.saveLogin(entity);	
				if(result) {
					return ApplicationConstants.LOGIN_SAVE_SUCCESS;
				}
			} else {
				throw new DuplicateIdException("Email Id already Found");
			}
		} catch(DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
		return "";
	}


	@Override
	public LoginDto getByEmail(String email) {
		logger.info("Entering getByEmail method");
		try {
			Login login = loginDao.getByEmail(email);
			if(login!=null) {
				return LoginUtil.toDto(login);
			} else {
				throw new NoRecordFoundException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
			
		} catch(DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String checkCredential(LoginDto loginDto) {
		logger.info("Entering checkCredential method");
		try {
			if(loginDao.getByEmail(loginDto.getEmail())!=null) {
			String result = null;
			Login login=LoginUtil.toEntity(loginDto);
			Login loginEntity=loginDao.getLoginByMail(login.getEmail());
			if (loginEntity != null && loginEntity.getPassword().equals(PasswordEncryptionUtil.getPassword(login.getPassword()))) {
				result=loginEntity.getRole();	
			}
			return result;
			}
			else {
				throw new NoRecordFoundException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
					
		}catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public String getRoleById(Long id) {
		logger.info("Entering getByEmail method");
		try {
			String role= loginDao.getRoleById(id);
			if(role!=null) {
				return role;
			} else {
				throw new NoRecordFoundException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
			
		} catch(DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String updatePassword(LoginDto loginDto, String password) {
		logger.info("Entering checkCredential method");
		try {
			if(loginDao.getByEmail(loginDto.getEmail())!=null) {
			String result = null;
			boolean value=false;
			Login login=LoginUtil.toEntity(loginDto);
			Login loginEntity=loginDao.getLoginByMail(login.getEmail());
			if (loginEntity != null && loginEntity.getPassword().equals(PasswordEncryptionUtil.getPassword(login.getPassword()))) {
				value=loginDao.updateLogin(login.getEmail(), password);
			}
			else {
				throw new NoRecordFoundException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
			if(value) {
				result="Password Updated Successfully";
			}
			return result;
			}
			else {
				throw new NoRecordFoundException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
					
		}catch (DataBaseException e) {
			logger.error(e.getMessage());
			throw new BusinessLogicException(e.getMessage());
		}

		
	}
	
	
	
	
}
