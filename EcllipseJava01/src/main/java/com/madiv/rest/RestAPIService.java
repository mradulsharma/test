package com.madiv.rest;

import java.net.HttpURLConnection;
import java.net.URL;

import com.jayway.jsonpath.JsonPath;


public class RestCaller {
	private String hostUrl, apiPath, cmkey;
	
	
	public RestCaller(String hostUrl, String apiPath, String cmkey) {
		this.setHostUrl(hostUrl);
		this.setApiPath(apiPath);
		this.setCmkey(cmkey);
	}
	
	
	public String getJsonValue(String jsonPath) throws Exception {
		String retValue = "";
		
				
		URL url = new URL(hostUrl + apiPath);	
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("cmkey", cmkey);

		if (conn.getResponseCode() != 200) {
			throw new Exception("HTTP error code : " + conn.getResponseCode());
		}

		
		retValue = JsonPath.read(conn.getInputStream(), jsonPath);
		
		
		conn.disconnect();

		
		
		return retValue;
	}
	

	public static void main(String[] args) {
		String hostUrl = "http://192.168.5.27/HR3RestApi/api/v1-0";
		String apiPath = "/Company";
		String cmkey = "6c97c097-0e43-427b-95a2-c9b20c52114f";	
		
		
		
		
		RestCaller restCaller = new RestCaller(hostUrl, apiPath, cmkey);
		String jsonValue;
		try {
			jsonValue = restCaller.getJsonValue("$.[0].Company_code");
			System.out.println("jsonValue = [" + jsonValue + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		


	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public String getCmkey() {
		return cmkey;
	}

	public void setCmkey(String cmkey) {
		this.cmkey = cmkey;
	}
	
}



