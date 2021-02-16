package com.madiv.gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestGson {
	private String sampleJson = "{\"PublicBackgroundCheckID\":251594,\"CheckType\":\"PC\",\"CurrentStatus\":null,\"NotificationType\":1,\"SystemID\":1,\"DeclineReason\":null,\"DeclineMessage\":null,\"Hash\":\"9B5D979A1A29E8C80A5C5722E17A2614\"} ";
	
	public static void main(String[] args) {
		TestGson testGson = new TestGson();
		testGson.doIt();
	}

	private void doIt() {
		
		// Parse and get jsonObject 
		JSONObject jsonObject = null;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(sampleJson);
        } catch (ParseException e) {
        	System.out.println("Unable to parse json");
        	e.printStackTrace();
        }
		
        // Print Json to String
        System.out.println("JSON raw:");
        System.out.println(jsonObject.toJSONString());
        
        
        //Beautify json and print.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(jsonObject.toJSONString());
        String prettyJsonString = gson.toJson(je);        
        System.out.println("\n\nJSON after beautifying for text:");
        System.out.println(prettyJsonString);
        
        
        System.out.println("\n\nJSON after beautifying for html:");
        System.out.println(prettyJsonString.replaceAll("\n", "<br>"));
        
        
        
        
	}
	
	
}
