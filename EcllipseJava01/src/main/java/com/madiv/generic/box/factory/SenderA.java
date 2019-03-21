package com.madiv.generic.box.factory;

import org.json.simple.JSONObject;

import com.madiv.generic.box.out.SenderResponse;
import com.madiv.generic.box.vo.VO;

public class SenderA extends SenderCommon implements Sender {

	@Override
	public SenderResponse processData(VO box) {
		System.out.println("Class :"+this.getClass().getName());
		
		System.out.println("Processing...");
		System.out.println("Start : Get Boxed Value");
		
		
		JSONObject json = (JSONObject)box.get();
		
		System.out.println(json.toJSONString());
		System.out.println("End : Get Boxed Value");
		
		
		
		return getResponse();
	}

}
