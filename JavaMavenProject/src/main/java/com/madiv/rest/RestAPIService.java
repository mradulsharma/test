package com.madiv.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;


public class RestAPIService {
	
	private String hostUrl, apiPath, cmkey, payload, method;
	private int responseCode;
	private JSONArray params;
	
	private  StringBuilder strBuilder = null;
	private StringBuilder errors = null;
	private StringBuilder urlString = null;
	
	private void initializeStringBuilder() {
		strBuilder = new StringBuilder();
		strBuilder.ensureCapacity(5 * RestAPIServiceUtil.KB); // Initialize String Builder to 5KB size.
		
		errors = new StringBuilder();
		errors.ensureCapacity(1 * RestAPIServiceUtil.KB); // Initialize String Builder to 1KB size.
		
		urlString = new StringBuilder();
		urlString.ensureCapacity(1 * RestAPIServiceUtil.KB); // Initialize String Builder to 1KB size.
	}
	
	
	public RestAPIService(String hostUrl, String apiPath, String cmkey, String payload, String method, JSONArray params) {
		
		initializeStringBuilder();
		
		this.setHostUrl(hostUrl);
		this.setApiPath(apiPath);
		this.setCmkey(cmkey);
		this.setPayload(payload);
		this.setMethod(method);
		this.setParams(params);
		
	}
	

	
	public void callAPI() throws IOException {
		HttpURLConnection conn = null;
		
		try {
			// Create Connection & add request parameter if required.
			conn = createAPIConnection();

			
			// if method is POST then Setup Payload
			if(this.method.equals("POST")) {
				addPayload(conn);
			}
			
			
			// Not response code received.
			this.responseCode = conn.getResponseCode();
			
			
			//Append response body into stringBuilder for further processing
			//we can't iterate directly on input stream otherwise it give error : "is closed occur at position -1".
			if(this.responseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
				strBuilder.append(IOUtils.toString(conn.getInputStream()));
			} else {
				strBuilder.append(IOUtils.toString(conn.getErrorStream()));
			}
			
			
		} catch (Exception e) {
			addResponseError("Something went wrong. Error => "+e.getMessage());
		} finally {
			//Close connection 
			conn.disconnect();
		} 
		
	}
	
	private String addParamsIfAny(String urlApiPath) throws UnsupportedEncodingException {
		
		
		//If params is not null, then send request parameters.
		if(this.params != null) {
			boolean firstParamSet = false;
			urlString.append("?");		
			for(Object param : this.params) {
				if(firstParamSet) urlString.append("&");
				
				String name = (String)((JSONObject)param).get(RestAPIServiceUtil.PARAM_NAME);
				String value = URLEncoder.encode(   (String)((JSONObject)param).get(RestAPIServiceUtil.PARAM_VALUE)  , "UTF-8");
				
				urlString.append(name + "=" + value);
				if(!firstParamSet) firstParamSet = true;
			}
		}
		
		//Encode url and return.
		return urlApiPath + urlString.toString();
	}



	public List<List<String>> grepValuesFromResponse(String[] grepArray) throws RestAPIServiceException {
		List<List<String>> retValue = new ArrayList<List<String>>();

		if (getResponseBody().length() > 0) { //if getResponseBody() has some contents.
			
			
			try { // Check if response is valid json or not, if not raise exception.
				JSONParser jsonParser = new JSONParser();
				Object object = jsonParser.parse(getResponseBody());
			} catch (ParseException e1) {
				throw new RestAPIServiceException("Response body is not valid json. Error => "+e1.getMessage()+". Response Body =>" + getResponseBody());
			}


			// Iterate over each grepArray and find values at individual jsonPath from response body.
			for (int i = 0; i < grepArray.length; i++) {
					Object value = JsonPath.read(getResponseBody(), grepArray[i]);
					retValue.add(RestAPIServiceUtil.getListFromObject(value));
			}

		}		
		
		
		return retValue;
	}
	
	
	
	

	private void addResponseError(String erorrMessage) {
		//If not first error message, then add pipe as message seperator.
		if (errors.length() != 0) {
			errors.append(RestAPIServiceUtil.ERROR_MESSAGE_SEPEARTOR);
		}
		errors.append(erorrMessage);
	}



	private void addPayload(HttpURLConnection conn) throws IOException {
        byte[] bytes = payload.getBytes();
        int length = bytes.length;
        conn.setRequestProperty("Content-Length", String.valueOf(length));

        OutputStream os = conn.getOutputStream();
        os.write(bytes);
        
        os.flush();
        os.close();
	}


	private HttpURLConnection createAPIConnection() throws IOException {
		
		String urlString = addParamsIfAny(this.hostUrl + this.apiPath);
		
		URL url = new URL(urlString);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(this.method);
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		conn.setRequestProperty("cmkey", cmkey);
		conn.setDoOutput(true);
		
		
		return conn;
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


	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public String getResponseBody() {
		return strBuilder.toString();
	}


	public int getResponseCode() {
		return responseCode;
	}

	public String[] getErrors() {
		return errors.toString().split("\\|");
	}



	public JSONArray getParams() {
		return params;
	}



	public void setParams(JSONArray params) {
		this.params = params;
	}

}



