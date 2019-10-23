package com.madiv.rest;

public class Test02 {
	public static void main(String[] args) {
		String potentialRecordId = "43652387/sajdfajsdsd/sadfsdf/asdf/asd";
		
		String output = potentialRecordId.substring(0, potentialRecordId.indexOf('/'));
		
		System.out.println("output=["+output+"]");
		
	}
	
	
	
}
