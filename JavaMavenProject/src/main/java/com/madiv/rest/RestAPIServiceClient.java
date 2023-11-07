package com.madiv.rest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestAPIServiceClient {
	private Map<String, JSONObject> callSequenceMap = new HashMap<String, JSONObject>();
	private String hostUrl, cmkey;
	
	public static void main(String[] args) throws IOException, ParseException {
		
		
//		String sequenceDefinationFile = "createEmployeeCallSequenceDefinition.json";
		String sequenceDefinationFile = "createEmployeeCallSequenceDefinitionFinal.json";
		
		
		
		RestAPIServiceClient client = new RestAPIServiceClient();
		try {
			client.doIt(RestAPIServiceUtil.getFileFromResources(sequenceDefinationFile));
		} catch (RestAPIServiceException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
    private void doIt(File fileFromResources) throws IOException, ParseException, RestAPIServiceException {
    	JSONObject callingDefinition = RestAPIServiceUtil.getJsonObject(fileFromResources);

    	//Populate important values from calling definition 
    	populateFromCallingDefinition(callingDefinition);
		
    	Map<String, Object> dataMap = getDataMap();
    	
    	
    	for(int i=1; i<callSequenceMap.size() + 1; i++) {
    		System.out.println("\n\n\n\n\n");
    		
    		
    		
    		String callSequence = Integer.toString(i);
    		
    		
    		//API Path 1
    		String apiPath = (String)callSequenceMap.get(callSequence).get(RestAPIServiceUtil.API_PATH);	//"/Company";
    		String apiPathWithMarkedUp = apiPath;
    		apiPath = RestAPIServiceUtil.checkForMarkUp(apiPath, dataMap);
    		
    		//Payload 1
    		String payload = null;
    		if(!callSequenceMap.get(callSequence).get(RestAPIServiceUtil.PAYLOAD_FILE).equals("null")) {
            	File file1 = RestAPIServiceUtil.getFileFromResources((String)callSequenceMap.get(callSequence).get(RestAPIServiceUtil.PAYLOAD_FILE));
        		payload = RestAPIServiceUtil.getJsonObject(file1).toJSONString();
        		payload = RestAPIServiceUtil.checkForMarkUp(payload, dataMap);
    		}
    		
    		//method 1
    		String method = (String)callSequenceMap.get(callSequence).get(RestAPIServiceUtil.METHOD);
    		
    		
    		//request parameter
    		JSONArray params = (JSONArray)callSequenceMap.get(callSequence).get(RestAPIServiceUtil.PARAMS);
    		if(params.size() != 0) {
    			String jsonArrayString = RestAPIServiceUtil.checkForMarkUp(params.toJSONString(), dataMap);
    			
    			JSONParser jsonParser=new JSONParser();
    			params = (JSONArray) jsonParser.parse(jsonArrayString);
    		}
    		
    		
    		//grep Array 1
    		String[] grepArray = toStringArray((JSONArray)callSequenceMap.get(callSequence).get(RestAPIServiceUtil.GREP_ARRAY));
    		String[] grepArrayWithMarkUp = grepArray.clone(); 
    		if(grepArray.length != 0) {
    			if(!(grepArray.length == 1 && grepArray[0].equals("${responseBody}"))) { 
    				// If this is grep array length is 1 and it first element content = ${responseBody} then no need to check for markup  
        			for(int grepArrayCount = 0; grepArrayCount < grepArray.length; grepArrayCount ++) {
        				grepArray[grepArrayCount] = RestAPIServiceUtil.checkForMarkUp(grepArray[grepArrayCount], dataMap);	
        			}
    				
    			}
    			
    		}

    		
    		
    		
    		System.out.println("hostUrl : ["+hostUrl+"]");
    		System.out.println("cmkey : ["+cmkey+"]");
    		System.out.println("apiPath : ["+apiPath+"]");
    		System.out.println("method : ["+method+"]");
    		System.out.println("grepArray : "+Arrays.toString(grepArray));
    		System.out.println("params : "+params);
    		System.out.println("\n");
    		

        	
        	
        	
    		RestAPIService restCaller = new RestAPIService(hostUrl, apiPath, cmkey, payload, method, params);
    		List<List<String>> grepArrayValues = null;
    		try {
    			
    			restCaller.callAPI();
    			if(!Arrays.toString(grepArrayWithMarkUp).equals("[${responseBody}]")) {
    				//Try getting grepArrayValues only if grepArray is not responeBody
    				grepArrayValues = restCaller.grepValuesFromResponse(grepArray);	
    			}
    			
    			
    			System.out.println("ResponseCode :  [" + restCaller.getResponseCode() + "]");
    			System.out.println("grepArrayValues.length = [" + ((grepArrayValues != null)?grepArrayValues.size():"null") + "]");
    			System.out.println("grepArrayValues contents :  " + grepArrayValues);
    			System.out.println("Response Body :  [" + restCaller.getResponseBody() + "]");
    			System.out.println("errors if any :  [" + Arrays.toString(restCaller.getErrors()) + "]");
    			
    			
    			// Populate dataMap 
    			if(apiPathWithMarkedUp.equals("/Company") && Arrays.toString(grepArrayWithMarkUp).equals("[$..[?(@.Company_code == ${hr3_company_code})].Company_Id]") && grepArrayValues != null) {
    				dataMap.put("companyId", grepArrayValues.get(0).get(0)); 
    			} else if (apiPathWithMarkedUp.equals("/employee/GetNextEmpno") && Arrays.toString(grepArrayWithMarkUp).equals("[${responseBody}]")) {
    				dataMap.put("empno", restCaller.getResponseBody().replace("\"", ""));
    			} else if (apiPathWithMarkedUp.equals("/employee/onboard") && Arrays.toString(grepArrayWithMarkUp).equals("[$.id]")) {
    				dataMap.put("empid", grepArrayValues.get(0).get(0)); 
    			} else if (apiPathWithMarkedUp.equals("/employee/${empid}/personalContacts") && Arrays.toString(grepArrayWithMarkUp).equals("[$.[0].Contact_Id]")) {
    				dataMap.put("employee_contact_id", grepArrayValues.get(0).get(0)); 
    			}
    			
    			
    			System.out.println("dataMap = " + dataMap);
    			
    			
    			
    			
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        	
    		
    		
    	} // End of for loop iterating over each call sequence
    	
    	
    	
    	
    	
		
	}

	private Map<String, Object> getDataMap() {
		
		String canidateIdentifier = "Oct21z";
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
    	dataMap.put("hr3_company_code", "BBE");
    	dataMap.put("candidate_last_name", "Sharma");
    	dataMap.put("candidate_first_name", "Maddy"+canidateIdentifier);
    	dataMap.put("candidate_email", "mraduls+"+canidateIdentifier+"@affirmsoftware.com.au");
    	dataMap.put("candidate_mobile_no", "0470173345");
    	dataMap.put("hr3_candidate_dob", "1978-09-30T00:00:00"); // Convert into YYYY-MM-DDTHH24:MI:SS format before setting to map.
    	dataMap.put("hr3_address_1", "3/58 Sutherland Rd");
    	dataMap.put("hr3_address_2", "");
    	dataMap.put("hr3_address_suburb", "Armadale");
    	dataMap.put("hr3_address_postcode", "3143");
    	dataMap.put("hr3_address_state", "VIC");
    	dataMap.put("hr3_candiate_gender", "M");
    	dataMap.put("hr3_emp_condition", "Bee-Keepers (QLD)");
    	dataMap.put("hr3_department", "OHS");
    	dataMap.put("hr3_cndt_title","Mr");
    	dataMap.put("start_date", "2019-12-01T00:00:00"); // Convert into YYYY-MM-DDTHH24:MI:SS format before setting to map.
    	dataMap.put("hr3_candiate_tfn_no", "111111111"); 
    	dataMap.put("hr3_pay_point", "Head Office");
    	dataMap.put("hr3_employment_status", "Full Time");
    	dataMap.put("hr3_pay_periods", "Weekly");
    	dataMap.put("hr3_tax_header", "1");
    	dataMap.put("hr3_pay_item", "P01");
    	dataMap.put("hr3_super_fund_api", "ASGARD");
    	dataMap.put("hr3_super_fund_member_no", "123456");
    	
    	dataMap.put("hr3_bank_branch_id", "2730");
    	dataMap.put("hr3_bank_account", "45698712");
    	//dataMap.put("hr3_bank_bsb", "062012"); We don't need that.
    	
    	dataMap.put("hr3_emergency_title_id", "3");
    	dataMap.put("hr3_emergency_contact_type_id", "7");
    	dataMap.put("hr3_emergency_contact_name", "Divya");
    	dataMap.put("hr3_emergency_contact_surname", "Nayak");
    	dataMap.put("hr3_emergency_contact_email", "dont.email.me@gmail.com");
    	dataMap.put("hr3_emergency_contact_mob_no", "0470486734");
    	dataMap.put("hr3_emergency_contact_mob_type_id", "3");
    	
    	
    	return dataMap;
	}



	private void populateFromCallingDefinition(JSONObject callingDefinition) {
		hostUrl = (String)callingDefinition.get(RestAPIServiceUtil.URL);
		cmkey = (String)callingDefinition.get(RestAPIServiceUtil.CMKEY);
		
		JSONArray callSequence = (JSONArray) callingDefinition.get(RestAPIServiceUtil.CALLING_SEQUENCE);
		//Itegrate over each calling sequence and push it into map.
		for(Object callingDetail : callSequence) {
			callSequenceMap.put((String)((JSONObject)callingDetail).get(RestAPIServiceUtil.SEQUENCE_NO), (JSONObject)callingDetail);
		}
		
	}
	
	
	private String[] toStringArray(JSONArray array) {
	    if(array==null)
	        return null;

	    String[] arr=new String[array.size()];
	    for(int i=0; i<arr.length; i++) {
	        arr[i]=(String)array.get(i);
	    }
	    return arr;
	}	



	
	
}
