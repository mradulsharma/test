package com.madiv.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.madiv.spring.aop.bean.TargetClass;

/**
 * 
 * @author Maddy
 *
 * Ref : https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/
 * 
 * 
 * Start : Terminologies
 * 
 * 	JoinPoints : Target class methods 
 * 	Advice : What to do.
 * 	Pointcuts : Where on joinpoints to call advice, e.g. @Before, @After, @Around, @AfterReturning, @AfterThrowing
 * 
 * 	Aspect : Advice + pointcuts = collection of advices. Which means, class having bunch of advice methods in it is called Aspect. 
 * 
 * End : Terminologies
 * 
 *
 */
public class AOPClient {
	private AbstractApplicationContext  context = null;
	
	
	public AOPClient() {
		this.context = new AnnotationConfigApplicationContext(AOPConfig.class);
	}
	
	
	public static void main(String[] args) throws Exception {
		
		AOPClient client = new AOPClient();
		client.doIt();

	}
	
	
	private void doIt() throws Exception {
		//TargetClass : calling processData
		TargetClass object = context.getBean(TargetClass.class);
		System.out.println("=============== 01 ================");
		System.out.println("Main : processingData = " + object.processData("This is argument 01"));
		System.out.println("===================================\n\n");

		System.out.println("=============== 02 ================");
		System.out.println("Main : New Age = " + object.findAgeAfter(10));
		System.out.println("===================================");
		
		
	}


	@Override
	protected void finalize() throws Throwable {
		context.close();
		super.finalize();
	}
	
}
