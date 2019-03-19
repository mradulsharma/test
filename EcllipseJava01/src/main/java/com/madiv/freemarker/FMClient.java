package com.madiv.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * 
 * @author Maddy
 *
 *
 * Free marker reference : https://docs.huihoo.com/freemarker/2.3.20/ref_directive_list.html
 * 
 * 
 */
public class FMClient {
	private final String outputFileName = "C:/Users/Maddy/Downloads/FreemarkerOutput.txt";
	
	public static void main(String[] args) {
		FMClient client = new FMClient();
		client.doIt();
	}

	private void doIt() {

		// Freemarker configuration object
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("sample.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("candidateName", "Candidate Name");
			data.put("candidateSalary", "$550,000.00");
			data.put("currentTimeStamp", getCurrentUTCTime());
			
			//Get list of string
			data.put("candidateSkillset", getCandidateSkillTest());

			//Get list of Objects
			data.put("candidateAddresses", getCandiateAddresses());

			//Get HashMap 
			data.put("candidateHashmap", getCandiateHashMap());
			
			// String output
			outputToString(template, data);

			// Console output
			// outputToConsole(template, data);

			// File output
			// writeToFile(template, data, outputFileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	private Map<String, String> getCandiateHashMap() {
		Map<String, String> hashMap = new HashMap<>();
		
		hashMap.put("K1", "Value 01");
		hashMap.put("K2", "Value 02");
		hashMap.put("K3", "Value 03");
		
		return hashMap;
	}

	private List<Address> getCandiateAddresses() {
		List<Address> addresses = new ArrayList<Address>();
		
		Address emailAddress = new Address("email", "mraduls@affirmsoftware.com.au");
		Address postalAddress = new Address("postal", "343 Lt Collins St, Melbourne, VIC 3000");
		Address webAddress = new Address("web", "www.test.com");

		addresses.add(emailAddress);
		addresses.add(postalAddress);
		addresses.add(webAddress);
		
		return addresses;
	}

	private void writeToFile(Template template, Map<String, Object> data, String outputFileName2) throws IOException, TemplateException {
		 Writer file = new FileWriter(new File(outputFileName2));
		 template.process(data, file);
		 file.flush();
		 file.close();
	}

	private void outputToConsole(Template template, Map<String, Object> data) throws TemplateException, IOException {
		Writer out = new OutputStreamWriter(System.out);
		template.process(data, out);
		out.flush();
	}
	
	private void outputToString(Template template, Map<String, Object> data) throws TemplateException, IOException {
		StringWriter out = new StringWriter();
		template.process(data, out);
		out.flush();
		
		System.out.println(out.getBuffer().toString());
	}

	

	private List<String> getCandidateSkillTest() {
		List<String> skillList = new ArrayList<String>();
		skillList.add("Java");
		skillList.add("J2EE");
		skillList.add("C++");
		skillList.add("HTML");
		return skillList;
	}
	
	
	private String getCurrentUTCTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}
	
}






/*
Output :





{
	"time_stamp":"20190319210310",
	"employee_name":"Candidate Name",
	"employee_salary":"$550,000.00",
	"employee_skills":[
		"1. Java",
		"2. J2EE",
		"3. C++",
		"4. HTML"
	],
	"employee_addresses":[
		{
			"employee_address_type":"email",
			"employee_address":"mraduls@affirmsoftware.com.au"
		},
		{
			"employee_address_type":"postal",
			"employee_address":"343 Lt Collins St, Melbourne, VIC 3000"
		},
		{
			"employee_address_type":"web",
			"employee_address":"www.test.com"
		}
	],
	"employee_keyvalue":[
		{
			"K3":"Value 03"
		},
		{
			"K1":"Value 01"
		},
		{
			"K2":"Value 02"
		}
	]
	
	
}


 */
