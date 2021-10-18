package com.rms.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.rms.exception.BusinessLogicException;

public class PasswordEncryptionUtil {
	
	private PasswordEncryptionUtil() {
		
	}

	
	public static String getPassword(String str) {
		 
		try{
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(str.getBytes());
         BigInteger no = new BigInteger(1, messageDigest);
         String hashtext = no.toString(16);
         while (hashtext.length() < 32) {
             hashtext = "0" + hashtext;
         }
         return hashtext;
         }
		 catch (NoSuchAlgorithmException e) {
			 throw new BusinessLogicException(e.getMessage());
	     }
	}
	
}
