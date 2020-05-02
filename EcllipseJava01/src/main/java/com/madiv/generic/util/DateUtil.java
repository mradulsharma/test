package com.madiv.generic.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String convertDate(String givenDate, String fromFormat, String toFormat) throws ParseException {
		/*
		 *  Converts date from one format to another.
		 *  e.g. from Date 	: dd/MM/yyyy
		 *  	 to date	: MM-dd-yyyy hh:mm:ss
		 */
		
		DateFormat srcDf = new SimpleDateFormat(fromFormat);
        
        // parse the date string into Date object
        Date date = srcDf.parse(givenDate);
         
        DateFormat destDf = new SimpleDateFormat(toFormat);
          
        // format the date into another format and return
        return destDf.format(date);
	}
	
	public static String currentDateInFormat(String format) throws ParseException {
		/*
		 *  returns current date in given format
		 *  e.g. format : yyyyMMdd
		 *  	 format : yyyyMMddhhmmss
		 */
		
        // Current Date object
        Date date = new Date();
         
        DateFormat destDf = new SimpleDateFormat(format);
          
        // format the date into another format and return
        return destDf.format(date);
	}
	
}
