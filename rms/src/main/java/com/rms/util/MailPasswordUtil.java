package com.rms.util;

import org.springframework.mail.javamail.JavaMailSender;

import com.rms.entity.Customer;

public class MailPasswordUtil {

	private MailPasswordUtil() {
		
	}
	
	public static void sendPassword(JavaMailSender javaMailSender,Customer customer) {

		String subject= "Your Temporary Password Details";
		
		String message="Hi "+customer.getName()+"\n\n"+"Your Temporary Password is "+customer.getPassword();
		
		MailSenderUtil.sendMail(javaMailSender,customer.getEmail(), subject, message);
		
	}
	
	
}
