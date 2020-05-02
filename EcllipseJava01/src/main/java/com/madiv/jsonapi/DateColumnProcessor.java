package com.madiv.jsonapi;

import java.text.ParseException;

import com.madiv.generic.util.DateUtil;

public class DateColumnProcessor implements ColumnProcessor {
	private static final String fromFormat = "dd/MM/yyyy";
	private static final String toFormat = "yyyy-MM-dd";

	@Override
	public String processColumnValue(String columnValue, String jsonKey) throws ParseException {
		
		return "\""+jsonKey+"\": \""+DateUtil.convertDate(columnValue, fromFormat, toFormat)+"\"";
	}
	
	public static void main(String[] args) throws Exception {
		ColumnProcessor processor = new DateColumnProcessor();
		System.out.println(processor.processColumnValue("6/04/2020", "job_startDate"));
	}

}
