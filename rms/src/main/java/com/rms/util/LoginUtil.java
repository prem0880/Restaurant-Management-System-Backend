package com.rms.util;

import com.rms.dto.LoginDto;
import com.rms.entity.Login;

public class LoginUtil {
	

		public static LoginDto toDto(Login login) {
			
			LoginDto loginDto = new LoginDto();
			loginDto.setId(login.getId());
			loginDto.setEmailId(login.getEmailId());
			loginDto.setPassword(login.getPassword());
			loginDto.setRole(login.getRole());
			return loginDto;
		}
		
		public static Login toEntity(LoginDto loginDto) {
			
			Login login = new Login();
			login.setId(loginDto.getId());
			login.setEmailId(loginDto.getEmailId());
			login.setPassword(loginDto.getPassword());
			login.setRole(loginDto.getRole());
			return login;
		}
		

}
