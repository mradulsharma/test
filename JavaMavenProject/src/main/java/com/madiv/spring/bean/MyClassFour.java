package com.madiv.spring.bean;

import org.springframework.stereotype.Component;

@Component("namedBean")
public class MyClassFour {
	public String getValue() {
		return this.getClass().getName();
	}
}
