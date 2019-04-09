package com.madiv.strbuilder;

public class StringBuilderClient {
	public static void main(String args[]) {
    	StringBuilder strBuilder = new StringBuilder();
    	
    	//Set initial capacity
    	strBuilder.ensureCapacity(128);
    	System.out.println("Initial Capacity ["+strBuilder.capacity()+"]");

    	//Appending some text in strBuilder
    	strBuilder.append("This is test");
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");

    	//Making strBuilder empty
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	
    	
    	//Appending some new text to strBuilder
    	strBuilder.append("Previous string was deleted, now this is new one");
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
	}
}
