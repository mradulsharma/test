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
		// This is example how a single line string template to use
		
		
		Configuration cfg = new Configuration();

		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("urlTempalte", "/paypoints/companycode/${companycode}");
		cfg.setTemplateLoader(stringLoader);
		Template template = cfg.getTemplate("urlTempalte");		
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("companycode", "BBE");
		data.put("pageSizeValue", "1000");
		
		
		StringWriter out = new StringWriter();
		template.process(data, out);
		out.flush();
		
		System.out.println("After replacing text :" + out.getBuffer().toString());
		System.out.println("Datamap :" + data);
		
		

	}
}
