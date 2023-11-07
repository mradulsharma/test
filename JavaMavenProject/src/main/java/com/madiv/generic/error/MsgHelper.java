package com.madiv.generic.error;

public class MsgHelper {
    public static String getMessage(final MsgCode code, final Object... args) {
        if (code == null) {
            return null;
        }
        String key = code.getKey();
        try {
        	
        	if(args == null || args.length == 0) return "["+key+"]-"+code.getBundle().getString(key);
        	else return String.format("["+key+"]-"+code.getBundle().getString(key), args);
        } catch (Exception e) {
            return "! " + code.getClass().getSimpleName() + "." + key + " !";
        }
    }
}
