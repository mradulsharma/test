package com.madiv.jackson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONObjectExample {
	
	String[] teamArray = {
			"Gauri",
			"Jo",
			"Lindsay",
			"Maddy",
			"Rachael",
			"Rythemi",
			"Viral"
	};
	
	public static void main(String[] args) {
		JSONObjectExample example = new JSONObjectExample();
		JSONObject json = example.createObject();
		
		
		
		System.out.println("JSON Constructed as : " + json);
	}

	public JSONObject createObject() {
		Map<String, String> keyValuemapCompanyInfoAndLeaders = getCompanyInfoAndLeaders();
		List<String> teamMembers = getTeamMembers();
		
		//construct JSON object
		JSONObject obj = new JSONObject();
		
		//Iterate over map and set key value in JSON object
		Iterator it = keyValuemapCompanyInfoAndLeaders.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        obj.put(pair.getKey(), pair.getValue());
	    }
	    
	    
		//Now add array in JSON object
	    JSONArray list = new JSONArray();
	    list.addAll(teamMembers);
	    obj.put("teamMembers", list);
		
		
	    return obj;
	}

	private List<String> getTeamMembers() {
		List<String> teamMemers = new ArrayList<String>(Arrays.asList(teamArray));   
		
		return teamMemers;
	}

	private Map<String, String> getCompanyInfoAndLeaders() {
		Map<String, String> keyValuemap = new HashMap<String, String>(); 
		
		keyValuemap.put("companyName", "Affirm Software Group");
		keyValuemap.put("director", "Richerd P");
		keyValuemap.put("ceo", "Bryan E");
		keyValuemap.put("marketingHead", "Michael H");
		keyValuemap.put("devLead", "Hock T");
		
		return keyValuemap;
	}

}
