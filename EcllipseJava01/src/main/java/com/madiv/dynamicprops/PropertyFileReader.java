package com.madiv.dynamicprops;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class PropertyFileReader {
    
    private static Map<String, ResourceBundle> bundleMap = new HashMap<String, ResourceBundle>(); 
    
    
    public static String getPropertyKeyValue(String fullyQualifiedPropertyFileName, String key){
        if( bundleMap.get(fullyQualifiedPropertyFileName) == null ) {
            loadPropertyFile(fullyQualifiedPropertyFileName);
        }
        
        return bundleMap.get(fullyQualifiedPropertyFileName).getString(key);
    }


    private static void loadPropertyFile(String fullyQualifiedPropertyFileName) throws MissingResourceException {
        ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(fullyQualifiedPropertyFileName);
        bundleMap.put(fullyQualifiedPropertyFileName, RESOURCE_BUNDLE);
    }
    
}
