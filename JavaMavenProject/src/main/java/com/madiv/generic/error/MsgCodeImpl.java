package com.madiv.generic.error;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MsgCodeImpl implements MsgCode{
	
	MADIV_01,
	MADIV_02,
	MADIV_03,
	MADIV_04,
	MADIV_05,
	MADIV_06,
	MADIV_07,
	MADIV_08,
	MADIV_09
	;
	
    private static final String BUNDLE_NAME = "com.madiv.generic.error.msg";
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
