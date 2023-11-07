package com.madiv.rest;

public class Test {
	
	public static void main(String[] args){
		String location = Test.class.getResource("Test02.class").getPath();
		
		System.out.println(location);
		
	}

} 
