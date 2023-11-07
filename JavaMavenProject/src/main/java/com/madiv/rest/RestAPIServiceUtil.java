package com.madiv.rest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class RestAPIServiceUtil {
	// Constants : Start
	public static final int KB = 1024;
	
	public static final String ERROR_MESSAGE_SEPEARTOR = "|";
	
	//JSON Definition Constants
	public static final String URL = "url";
	public static final String CMKEY = "cmkey";
	public static final String CALLING_SEQUENCE = "callingSequence";
	
	public static final String SEQUENCE_NO = "sequenceNo";
	public static final String API_PATH = "apiPath";
	public static final String PAYLOAD_FILE = "payloadFile";
	public static final String METHOD = "method";
	public static final String PARAMS = "params";
	public static final String GREP_ARRAY = "grepArray";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_VALUE = "value";
	
	//Regex
	public static final String IS_MARKEDUP_PATTERN = ".*\\$\\{(.*?)\\}.*";
	
	
	
	// Constants : End
	
	
	public static JSONObject getJsonObject(File josnFilePath) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(josnFilePath);
		return (JSONObject) jsonParser.parse(reader);
	}
	
	
	
	
	// get file from classpath, resources folder
    public static File getFileFromResources(String fileName) {

        ClassLoader classLoader = RestAPIServiceUtil.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File \""+fileName+"\" is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
    
    
	public static boolean isMarkedup(String text) {
		boolean retValue = false;

		if (getMarkedUpText(text) != null) {
			retValue = true;
		} else {
			retValue = false;
		}

		return retValue;
	}


	public static String getMarkedUpText(String text) {
		String retValue = null;

		Pattern pattern = Pattern.compile(IS_MARKEDUP_PATTERN);
		Matcher matcher = pattern.matcher(text);

		if (matcher.matches()) {
			retValue = matcher.group(1);
		} else {
			retValue = null;
		}


		return retValue;
	}
    

	
	public static String checkForMarkUp(String inputText, Map<String, Object> markUpData) throws RestAPIServiceException {
		String retValue = inputText;
		if(isMarkedup(inputText)) {

			try {

				//TODO : Review commanets : - Free marker tempalte code replacement to manual string find and replce.
				Configuration cfg = new Configuration();
				StringTemplateLoader stringLoader = new StringTemplateLoader();
				stringLoader.putTemplate("testTemplate", inputText);
				cfg.setTemplateLoader(stringLoader);

				Template template = cfg.getTemplate("testTemplate");

				StringWriter out = new StringWriter();
				template.process(markUpData, out);
				out.flush();

				retValue = out.getBuffer().toString();

			} catch (IOException | TemplateException e) {
				String msg = "Something went wrong while marking up apiPath ["+inputText+"]. MarkUpDataMap =["+markUpData+"] ";
				throw new RestAPIServiceException(msg);
			}

		}

		return retValue;
	}
	
	
	public static List<String> getListFromObject(Object incomingObject) {
		List<String> retValue = new ArrayList<String>();
		
		if(incomingObject instanceof List<?>) {
			retValue = (List<String>)incomingObject;
		} else {
			retValue.add(incomingObject.toString());
		}
		
		return retValue;
	}
	
	
	
}
