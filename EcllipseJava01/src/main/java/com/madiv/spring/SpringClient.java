package com.madiv.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.madiv.spring.bean.MyClassOne;
import com.madiv.spring.bean.MyClassTwo;


public class SpringClient {
	private AbstractApplicationContext  context = null;
	
	public SpringClient() {
		context = new AnnotationConfigApplicationContext(SpringConfig.class);
	}
	
	
	public static void main(String[] args) {
		SpringClient client = new SpringClient();
		client.doIt();
	}
	
	
	private void doIt() {
		MyClassOne one = context.getBean(MyClassOne.class);
		System.out.println("one.getValue()="+one.getValue());
		System.out.println("one"+one);
		MyClassOne one1 = context.getBean(MyClassOne.class);
		System.out.println("one1"+one1);
		
		MyClassTwo two = context.getBean(MyClassTwo.class);
		System.out.println("two.getValue()="+two.getValue());
		System.out.println("two"+two);
		MyClassTwo two2 = context.getBean(MyClassTwo.class);
		System.out.println("two2"+two2);
		
		
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		context.close();
		super.finalize();
	}
}
