package com.madiv.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.madiv.spring.bean.MyClassFour;
import com.madiv.spring.bean.MyClassOne;
import com.madiv.spring.bean.MyClassThree;
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
		//MyClassOne : Singleton Scope
		MyClassOne one = context.getBean(MyClassOne.class);
		System.out.println("one.getValue()="+one.getValue());
		System.out.println("one"+one);
		MyClassOne one1 = context.getBean(MyClassOne.class);
		System.out.println("one1"+one1+"\n\n");
		
		//MyClassTwo : Singleton Scope
		MyClassTwo two = context.getBean(MyClassTwo.class);
		System.out.println("two.getValue()="+two.getValue());
		System.out.println("two"+two);
		MyClassTwo two2 = context.getBean(MyClassTwo.class);
		System.out.println("two2"+two2+"\n\n");
		

		//MyClassThree : Prototype Scope
		MyClassThree three = context.getBean(MyClassThree.class);
		System.out.println("three.getValue()="+three.getValue());
		System.out.println("three"+three);
		MyClassThree three3 = context.getBean(MyClassThree.class); // This one is new object becasue of prototype scope.
		System.out.println("three3"+three3+"\n\n");
		
		//MyClassFour : namedBean
		MyClassFour four = (MyClassFour)context.getBean("namedBean");
		System.out.println("four.getValue()="+four.getValue());
		System.out.println("four"+four);
		MyClassFour four4 = (MyClassFour)context.getBean("namedBean"); // This one is new object becasue of prototype scope.
		System.out.println("four4"+four4+"\n\n");
		
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		context.close();
		super.finalize();
	}
}
