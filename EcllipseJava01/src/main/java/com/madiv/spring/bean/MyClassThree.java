package com.madiv.spring.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class MyClassThree {
	public String getValue() {
		return this.getClass().getName();
	}
}
