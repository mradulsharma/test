package com.madiv.concurrent.reentrantlock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountUtil {
	private final static SimpleDateFormat time_rightNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static String getCurrentTime() {
		return time_rightNow.format(new Date()) + " : ";
	}

}
