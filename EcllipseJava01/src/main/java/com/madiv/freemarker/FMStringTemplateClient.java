package com.madiv.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FMStringTemplateClient {
	public static void main(String[] args) throws TemplateException, IOException {
		// This is example how a single line string template to use, this will also check if else condition checking if data is available in map or not.
		
		
		Configuration cfg = new Configuration();

		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("urlTempalte", "/paypoints/companycode/<#if companycode1?? && companycode2??>${companycode1} + ${companycode2}<#elseif companycode2??>${companycode2}<#else>${default}</#if>");
		cfg.setTemplateLoader(stringLoader);
		Template template = cfg.getTemplate("urlTempalte");		
		
		
		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("companycode1", "BBE");
//		data.put("companycode2", "CAT");
		data.put("default", "testing");
		data.put("pageSizeValue", "1000");
		
		
		StringWriter out = new StringWriter();
		template.process(data, out);
		out.flush();
		
		System.out.println("After replacing text :" + out.getBuffer().toString());
		System.out.println("Datamap :" + data);
		
		

	}
}
