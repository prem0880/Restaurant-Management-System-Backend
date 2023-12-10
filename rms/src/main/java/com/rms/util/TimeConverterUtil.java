package com.rms.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeConverterUtil {
	
	private TimeConverterUtil() {
		
	}
	
	  private static final LocalTime MORNING = LocalTime.of(0, 0, 0);
	  private static final LocalTime AFTER_NOON = LocalTime.of(11, 59, 59);
	  private static final LocalTime EVENING = LocalTime.of(17, 59, 59);
	  private static final LocalTime NIGHT = LocalTime.of(23, 59, 59);
	  	  
	  private static LocalTime now;
	  
	  public static String getTime(LocalTime loggedTime)
	  {
		 String time=null;
		now = loggedTime.truncatedTo(ChronoUnit.SECONDS);
		 if (between(MORNING, AFTER_NOON)) {
		      time="Breakfast";
		 } 
		 else if (between(AFTER_NOON, EVENING)) {
		      time="Lunch";
	     } 
		 else if (between(EVENING, NIGHT)) {
		     time="Dinner";
		 } 
		 return time;
		
	  }
	  
	  private static boolean between(LocalTime start, LocalTime end) {
		    return (!now.isBefore(start)) && now.isBefore(end);
	  }
}
