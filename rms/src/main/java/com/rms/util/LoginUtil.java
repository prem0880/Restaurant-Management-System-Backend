package com.rms.util;

import com.rms.dto.LoginDto;
import com.rms.entity.Login;

public class LoginUtil {
	

		public static LoginDto toDto(Login login) {
			
			LoginDto loginDto = new LoginDto();
			loginDto.setId(login.getId());
			loginDto.setEmail(login.getEmail());
			loginDto.setPassword(login.getPassword());
			loginDto.setRole(login.getRole());
			System.out.println("util1"+loginDto.getEmail());
			return loginDto;
		}
		
		public static Login toEntity(LoginDto loginDto) {
			
			Login login = new Login();
			login.setId(loginDto.getId());			
			login.setEmail(loginDto.getEmail());
			login.setPassword(loginDto.getPassword());
			login.setRole(loginDto.getRole());
			System.out.println("util2"+login.getEmail());
			return login;
		}
		

}
