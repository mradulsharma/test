package com.madiv.generic.box;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
	private List<String> csvRow = new ArrayList<>();
	
	public void add(String[] values) {
		csvRow.addAll(Arrays.asList(values));
	}
	
	public void add(List<String> values) {
		csvRow.addAll(values);
	}
	
	public String formateToCSV() {
		return String.join(",", csvRow);
	}
	
	@Override
	public String toString() {
		return csvRow.toString();
	}
	
}
