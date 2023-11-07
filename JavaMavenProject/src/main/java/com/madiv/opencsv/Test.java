package com.madiv.opencsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {


		
		
		
		List<String[]> myListInput = new ArrayList<>();
		List<String[]> myListOutput = new ArrayList<>();
		Map<String, Integer> maxValue = new HashMap<>();
		
		String[] line1 = {"Retail Employees Superannuation Trust", "", ""};
		String[] line2 = {"Retail Employees Superannuation Trust", "", ""};
		String[] line3 = {"Retail Employees Superannuation Trust", "Australian Super", ""};
		String[] line4 = {"Retail Employees Superannuation Trust", "Australian Super", ""};
		String[] line5 = {"Retail Employees Superannuation Trust", "Australian Super", ""};
		
		myListInput.add(line1);
		myListInput.add(line2);
		myListInput.add(line3);
		myListInput.add(line4);
		myListInput.add(line5);
		
		
		//Finding max value
		int maxValueSizeForThisRecord = -1;
		for(String[] line : myListInput) {
//			System.out.println(Arrays.toString(line));
			maxValue.put(Arrays.toString(line), Arrays.toString(line).length());
			
			if (Arrays.toString(line).length() > maxValueSizeForThisRecord) {
				maxValueSizeForThisRecord = Arrays.toString(line).length();
			}
		}
		
		
//		int maxSize = getMaxSize(maxValue);
		
		
		//Generating output array
		for(String[] line : myListInput) {
			String[] lineOut = new String[line.length + 1];
			System.arraycopy(line, 0, lineOut, 0, line.length);
			lineOut[line.length] = (Arrays.toString(line).length() == maxValueSizeForThisRecord)?"Y":" ";
//			System.out.println(Arrays.toString(line) + " -> " + maxValue.get(Arrays.toString(line)));
			System.out.println("Out =>" + Arrays.toString(lineOut));
		}
		
		
		
		System.out.println("Map => "+maxValue);
		System.out.println("myListInput => "+myListInput);
		myListInput.clear();
		System.out.println("myListInput => "+myListInput);
		
	}

	private static int getMaxSize(Map<String, Integer> maxValue) {
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
