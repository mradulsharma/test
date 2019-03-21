package com.madiv.generic.box;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.madiv.generic.box.factory.Sender;
import com.madiv.generic.box.factory.SenderFactory;
import com.madiv.generic.box.out.SenderResponse;
import com.madiv.generic.box.vo.AVO;
import com.madiv.generic.box.vo.BVO;
import com.madiv.jackson.JSONObjectExample;

public class SenderClient {
	public static void main(String[] args) {
		SenderClient client = new SenderClient();
		client.doA();
		client.doB();
	}

	private void doB() {
		System.out.println("**********************");
		Sender sender = SenderFactory.getSender("B");
		
		CSV csv = getCSVObject();
		BVO<CSV> bvo = new BVO<>();
		bvo.set(csv);
		
		SenderResponse response = sender.processData(bvo);
		System.out.println("A Response : " + response);
		System.out.println("**********************");
		
	}

	private void doA() {
		System.out.println("**********************");
		Sender sender = SenderFactory.getSender("A");
		
		
		JSONObject object = getJsonObject();
		AVO<JSONObject> avo = new AVO<>();
		avo.set(object);
		
		SenderResponse response = sender.processData(avo);
		System.out.println("B Response : " + response);
		System.out.println("**********************");
	}
	
	
	
	private JSONObject getJsonObject() {
		JSONObjectExample example = new JSONObjectExample();
		return example.createObject();
	}
	
	
	private CSV getCSVObject() {
		CSV csv = new CSV();
		
		csv.add("Hello,World".split(","));
		
		
		List<String> list = new ArrayList<>();
		list.add("from");
		list.add("Melbourne");
		csv.add(list);
		
		return csv;
	}
}
