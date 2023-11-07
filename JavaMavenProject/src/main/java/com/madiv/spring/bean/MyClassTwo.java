package com.madiv.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class MyClassTwo {
	public String getValue() {
		return this.getClass().getName();
	}
}
