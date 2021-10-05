package com.rms.service;

import com.rms.dto.LoginDto;

public interface LoginService {
	String saveLogin(LoginDto loginDto);
	String updateLogin(String email, String password);
	LoginDto getByEmail(String email);
	String forgotPassword(String email, String password);
}
