package com.madiv.strbuilder;

public class StringBuilderClient {
	public static void main(String args[]) {
    	StringBuilder strBuilder = new StringBuilder();
    	
    	//Set initial capacity
    	strBuilder.ensureCapacity(128);
    	System.out.println("Initial Capacity ["+strBuilder.capacity()+"]");

    	//Appending null and see what is strBuilder lenght
    	String str = null;
    	strBuilder.append(str);
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("After strBuilder.delete(0, strBuilder.length()) => String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]\n");
    	
    	str = "";
    	strBuilder.append(str);
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("After strBuilder.delete(0, strBuilder.length()) => String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]\n");
    	
    	//Appending some text in strBuilder
    	strBuilder.append("This is test");
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	//Making strBuilder empty
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("After strBuilder.delete(0, strBuilder.length()) => String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]\n");
    	
    	
    	//Appending some new text to strBuilder
    	strBuilder.append("Previous string was deleted, now this is new one");
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("After strBuilder.delete(0, strBuilder.length()) => String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]\n");
    	
    	
    	//Appending some new text more than capacity
    	strBuilder.append("Appending some new text more than capacity. Appending some new text more than capacity. Appending some new text more than capacity. Appending some new text more than capacity");
    	System.out.println("String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]");
    	strBuilder.delete(0, strBuilder.length());
    	System.out.println("After strBuilder.delete(0, strBuilder.length()) => String ["+strBuilder.toString()+"], lenght ["+strBuilder.length()+"], capacity ["+strBuilder.capacity()+"]\n");
    	
	}
}
