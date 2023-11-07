package com.madiv.generic.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringUtilTest {
	private static String testCSVString = null;

	@BeforeClass
	public static void prepareForTest() {
		//Executed once, before the start of all tests
		testCSVString = "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01"; 
	}
	
	
    @Test
	public void testStringUtilCSV() {
    	
    	assertNull(StringUtil.getPropertyValue("Key00", "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01"));
    	assertEquals("Value01", StringUtil.getPropertyValue("Key01", "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01"));
    	assertEquals("Value03", StringUtil.getPropertyValue("Key03", "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01"));
    	assertNotEquals("Value03", StringUtil.getPropertyValue("Key04", "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01"));
    	
	}
    
    
    @AfterClass
    public static void clearAll() {
    	//Executed once, after all tests have been finished
    	testCSVString = null;
    }
    
}
