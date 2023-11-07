package com.madiv.jsonapi;

public interface ColumnProcessor {
	public String processColumnValue(String columnValue, String jsonKey) throws Exception;
}
