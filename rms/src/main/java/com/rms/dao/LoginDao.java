package com.rms.dao;

import com.rms.entity.Login;

public interface LoginDao {
	boolean saveLogin(Login login);
	boolean updateLogin(String email, String password);
	Login getByEmail(String email);
	Login getLoginByMail(String email);
	String getRoleById(Long id);
}
