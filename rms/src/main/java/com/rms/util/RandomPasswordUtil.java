package com.rms.util;

import java.util.Random;

public class RandomPasswordUtil {

	public static String getRandomPassword() {
		
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder pass = new StringBuilder();
        while (pass.length() <= 8) { 
            int index = (int) (new Random().nextFloat() * str.length());
            pass.append(str.charAt(index));
        }
        return pass.toString();
	}
	
}
