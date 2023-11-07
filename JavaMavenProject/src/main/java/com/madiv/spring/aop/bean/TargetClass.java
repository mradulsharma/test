package com.madiv.spring.aop.bean;

import org.springframework.stereotype.Component;

@Component
public class TargetClass {
	private String name = "Maddy";
	private Integer age = 40;
	
	public String processData(String argument) {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassMemberVariable:{name="+name+", age="+age+"}\n");
		builder.append("passedArgument:{argument="+argument+"}");
		
		return builder.toString();
	}
	
	
	public Integer findAgeAfter(Integer year) throws Exception {
		
		if(year == null) {
			throw new Exception("Generic Error");
		}
		
		return this.age + year;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
