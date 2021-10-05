package com.rms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderUtil {

	
	private static JavaMailSender javaMailSender;
	
	@Autowired
    public MailSenderUtil(JavaMailSender s) {
    	MailSenderUtil.javaMailSender=s;
    }
	
	public static  void sendMail(String toReceiver,String subject,String message){		
	    
    	SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(toReceiver);
		mail.setSubject(subject);
		mail.setText(message);
		javaMailSender.send(mail);	
	}
}
