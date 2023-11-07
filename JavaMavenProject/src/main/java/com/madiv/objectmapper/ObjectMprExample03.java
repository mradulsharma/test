package com.madiv.objectmapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author Maddy
 * 
 * 
 * Convert a pojo into json and vice a versa  
 * 
 */
public class ObjectMprExample03 {
	public static void main(String[] args) {
		
		ObjectMprExample03 example01 = new ObjectMprExample03();
		try {
			example01.doIt();
		} catch (JsonProcessingException e) {
			System.out.println("Error Occured : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error Occured : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	private void doIt() throws IOException {
		ObjectMapper objMapper = new ObjectMapper();
		
		// Annotated object to json string
		StudentAnnotated studentAnnotated = getAnnotatedStudent();
		String jsonString01 = objMapper.writeValueAsString(studentAnnotated);
		System.out.println("jsonString Annotated pojo object="+jsonString01);
		
		
		
		// Simple object to json String
		StudentSimple studentSimple = getSimpleStudent();
		String jsonString02 = objMapper.writeValueAsString(studentSimple);
		System.out.println("jsonString Simple pojo object="+jsonString02);

		
		
		
		// String to json simple object
		String jsonStringSimple = "{\"firstName\":\"Ayaananand\",\"lastName\":\"Nayak\",\"rollNumber\":\"SONIC_0002\"}";
		StudentSimple studentSimple2 =  objMapper.readValue(jsonStringSimple, StudentSimple.class);
		System.out.println("studentSimple2 = " + studentSimple2);
		
		
		
		
		// String to json annotated object
		String jsonStringAnnotated = "{\"STUDENT_FIRST_NAME\":\"Ayu\",\"STUDENT_LAST_NAME\":\"Pagal\",\"STUDENT_ROLL_NUMBER\":\"SONIC_0003\"}";
		StudentAnnotated studentAnnotated2 =  objMapper.readValue(jsonStringAnnotated, StudentAnnotated.class);
		System.out.println("studentAnnotated2 = " + studentAnnotated2);
		
		
	}

	private StudentSimple getSimpleStudent() {
		StudentSimple studentSimple = new StudentSimple();
		studentSimple.setFirstName("Ayaan");
		studentSimple.setLastName("Sharma");
		studentSimple.setRollNumber("SONIC_0001");
		return studentSimple;
	}

	private StudentAnnotated getAnnotatedStudent() {
		StudentAnnotated studentAnnotated = new StudentAnnotated();
		studentAnnotated.setFirstName("Ayaan");
		studentAnnotated.setLastName("Sharma");
		studentAnnotated.setRollNumber("SONIC_0001");
		return studentAnnotated;
	}
}



