package com.rms.util;

import java.util.Random;

public class RandomPassword {
	
	private RandomPassword() {
		
	}

public static String getRandomPassword() {
		
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        while (sb.length() <= 8) { 
            int index = (int) (new Random().nextFloat() * str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString();
	}
}
