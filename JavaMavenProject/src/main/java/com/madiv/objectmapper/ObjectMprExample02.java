package com.madiv.objectmapper;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author Maddy
 * 
 * 
 * Convert a pojo into simple Map having defined property naem to be used as key and value as value of Map  
 * 
 */
public class ObjectMprExample02 {
	public static void main(String[] args) {
		
		ObjectMprExample02 example01 = new ObjectMprExample02();
		example01.doIt();
		
	}

	private void doIt() {
		ObjectMapper objMapper = new ObjectMapper();
		
		StudentAnnotated student = new StudentAnnotated();
		student.setFirstName("Ayaan");
		student.setLastName("Sharma");
		student.setRollNumber("SONIC_0001");
		
		Map<String, Object> objectToMap = objMapper.convertValue(student, Map.class);
		
		System.out.println("objectToMap="+objectToMap);
		
	}
}



