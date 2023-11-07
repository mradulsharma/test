package com.madiv.generic.context;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Props implements P{
	
	JAR_ECLIPSE_PROJECT_PATH,
	JAR_GIT_PROJECT_SOANTA,
	JAR_GIT_PROJECT_SOANTA_API,
	JAR_EXTRACT_FOLDER_PATH
	;
	
    private static final String BUNDLE_NAME = "com.madiv.generic.context.applicationcontext";
    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
	
	@Override
	public String getKey() {
        return toString();
	}

	@Override
	public ResourceBundle getBundle() {
        if (RESOURCE_BUNDLE == null) {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
        }
        return RESOURCE_BUNDLE;
	}

}
