package com.madiv.generic.box.factory;

public class SenderFactory {
	public static Sender getSender(String type) {
		Sender retValue = null;
		
		if("A".equals(type)) {
			retValue = new SenderA();
		}else if ("B".equals(type)) {
			retValue = new SenderB();
		}
		
		return retValue;
	}
}
