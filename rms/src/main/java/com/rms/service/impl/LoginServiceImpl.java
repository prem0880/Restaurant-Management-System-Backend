package com.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.constants.ApplicationConstants;
import com.rms.dao.LoginDao;
import com.rms.dto.LoginDto;
import com.rms.entity.Login;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.LoginService;
import com.rms.util.LoginUtil;
import com.rms.util.MailSenderUtil;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public String saveLogin(LoginDto loginDto) {
		try {
			Login entity = LoginUtil.toEntity(loginDto);
			Login loginCheck = null;
			loginCheck = loginDao.getByEmail(loginDto.getEmailId());
			if(loginCheck == null) {
				boolean result = loginDao.saveLogin(entity);	
				if(result) {
					String msg = "Your Temporary Password for login is your registered phone number /n Please change your password after login by Change your password option in your profile";
					MailSenderUtil.sendMail(loginDto.getEmailId(), "Temporary Password For Login", msg);
					return ApplicationConstants.LOGIN_SAVE_SUCCESS;
				}
			} else {
				throw new BusinessLogicException("Email Id already Found");
			}
		} catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
		return "";
	}

	@Override
	public String updateLogin(String email, String password) {
		try {
			Login loginCheck = null;
			loginCheck = loginDao.getByEmail(email);
			if(loginCheck != null) {
				boolean result = loginDao.updateLogin(email, password);
				if(result)
					return "Password Updated Successfully";
			} else {
				throw new BusinessLogicException("Customer Id Not Found");
			} 
		} catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
		return "";
	}

	@Override
	public LoginDto getByEmail(String email) {

		try {
			Login login = null;
			login = loginDao.getByEmail(email);
			if(login != null) {
				return LoginUtil.toDto(login);
			} else {
				throw new BusinessLogicException(ApplicationConstants.LOGIN_NOT_FOUND);
			}
		} catch(DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public String forgotPassword(String email, String password) {

		try {
			String message = updateLogin(email, password);
			if(!message.equals("")) {
				String msg = "As requested for forgot password, Your Password for login is your registered phone number. Please change your password after login by Change your password option in your profile";
				MailSenderUtil.sendMail(email, "Temporary Password For Login", msg);
				return "Password sent to your mail Successfully";
			}
		} catch(BusinessLogicException e) {
			throw new BusinessLogicException(e.getMessage());
		}
		return "";
	}
	
	
	
	
}
