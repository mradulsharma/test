package com.madiv.csv;

import com.madiv.generic.util.StringUtil;

public class CSVExtractClient {
	public static void main(String[] args) {
		CSVExtractClient client = new CSVExtractClient();
		client.runDemo();
	}

	private void runDemo() {
    	for(int i=0; i<6; i++) {
    		String key = "Key0"+i;
    		String value = StringUtil.getPropertyValue(key, "Key04=Value04, Key03=Value03, Key02=Value02, Key01=Value01");
        	System.out.println("Key=["+key+"], Value=["+value+"]");
    	}
	}
}


/*
Output =>

Key=[Key00], Value=[null]
Key=[Key01], Value=[Value01]
Key=[Key02], Value=[Value02]
Key=[Key03], Value=[Value03]
Key=[Key04], Value=[Value04]
Key=[Key05], Value=[null]
 
*/

