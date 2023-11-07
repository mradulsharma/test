package com.madiv.generic.context;

public class ApplicationContext {
    public static String getProperty(final P code) {
        if (code == null) {
            return null;
        }
        String key = code.getKey();
        try {
        	
        	return code.getBundle().getString(key);
        	
        } catch (Exception e) {
            return "! " + code.getClass().getSimpleName() + "." + key + " !";
        }
    }
}
