package com.madiv.objectmapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentAnnotated {
	
	@JsonProperty("STUDENT_FIRST_NAME")
	private String firstName = null;
	
	@JsonProperty("STUDENT_LAST_NAME")
	private String lastName = null;
	
	@JsonProperty("STUDENT_ROLL_NUMBER")
	private String rollNumber = null;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	@Override
	public String toString() {
		return "StudentAnnotated [firstName=" + firstName + ", lastName=" + lastName + ", rollNumber=" + rollNumber + "]";
	}
}