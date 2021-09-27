package com.rms.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimeStamp {

	public static Timestamp getTimeStamp() {
		Date date= new Date();
		long time = date.getTime();
        return new Timestamp(time);
	}
}
