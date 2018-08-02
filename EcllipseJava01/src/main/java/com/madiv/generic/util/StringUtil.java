package com.madiv.generic.util;


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
    
    
}
