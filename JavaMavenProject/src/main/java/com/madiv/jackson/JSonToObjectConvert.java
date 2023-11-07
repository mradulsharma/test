package com.madiv.jackson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonToObjectConvert {
	
	private String feedLocation = "C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\02.Enhancements\\01-Expr3ss_Integration\\feed.txt";
	
	public static void main(String[] args) {
		JSonToObjectConvert convert = new JSonToObjectConvert();
		convert.doit();
	}

	private void doit() {
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(feedLocation));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
		} catch (IOException | ParseException e) {
			System.out.println("Unable to convert to JSONObject : "+e.getMessage());
			e.printStackTrace();
		}		
		
		
		System.out.println("jsonObject ="+jsonObject);
		
		
	}
}



