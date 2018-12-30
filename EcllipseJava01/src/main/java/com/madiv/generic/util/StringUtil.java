package com.madiv.generic.util;

import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String s, boolean ignoreWhite) {
        return (s == null || (0 == (ignoreWhite ? s.trim().length() : s.length())));
    }
    
    public static boolean isEmpty(String s) {
        return isEmpty(s, true);
    }

    public static String firstUpper(String line){
        String retValue = null;
        if(line != null ) retValue = line.substring(0, 1).toUpperCase() + line.substring(1); 
        return retValue;
    }
    
    public static String getPropertyValue(String key, String csvString){
    	String retValue = null;
    	
    	Pattern p = Pattern.compile("[\\{\\}\\=\\, ]++");
    	String[] split = p.split(csvString);
    	for ( int i=0; i+2 <= split.length; i+=2 ){
    	    
    		if(split[i].equals(key.trim())) {
    			retValue =  split[i+1].trim();
    			break;
    		}
    		
    	}
    	return retValue;    	
    	
    }
    
    
}
