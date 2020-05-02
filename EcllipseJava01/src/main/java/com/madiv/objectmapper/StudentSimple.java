package com.madiv.objectmapper;

public class StudentSimple {
	private String firstName = null;
	private String lastName = null;
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
		return "StudentSimple [firstName=" + firstName + ", lastName=" + lastName + ", rollNumber=" + rollNumber + "]";
	}
}