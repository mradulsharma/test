package com.madiv.jsonapi;

public class DefaultColumnProcessor implements ColumnProcessor {

	@Override
	public String processColumnValue(String columnValue, String jsonKey) {
		return "\""+jsonKey+"\": \""+columnValue.trim()+"\"";
	}
	
	public static void main(String[] args) throws Exception {
		ColumnProcessor processor = new DefaultColumnProcessor();
		System.out.println(processor.processColumnValue("1245sdfas", "applicant_id"));
	}

}
