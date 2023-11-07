package com.madiv.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class MyClassOne {
	public String getValue() {
		return this.getClass().getName();
	}

}
