package com.madiv.spring.aop.bean;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	@Before("execution(* com.madiv.spring.aop.bean.TargetClass.processData(..))")
	private void adviceOne(JoinPoint joinPoint) {
		System.out.println("@Before Advice : start");
		accessTargetClass(joinPoint);
		System.out.println("@Before Advice : end\n\n");
	}
	
	
	@AfterReturning(pointcut="execution(* com.madiv.spring.aop.bean.TargetClass.processData(..))",returning= "result")
	private void adviceTwo(JoinPoint joinPoint, Object result) {
		System.out.println("@AfterReturning Advice : start"); 
		accessTargetClass(joinPoint);
		System.out.println("@AfterReturning Method returned value is : " + result);
		System.out.println("@AfterReturning Advice : end\n\n");
	}
	
	
	@Around("execution(* com.madiv.spring.aop.bean.TargetClass.findAgeAfter(..))")
	private Object adviceThree(ProceedingJoinPoint joinPoint) {
		Object retValue = null;
		
		System.out.println("@Around Advice : start");
		System.out.println("@Around Arguments to pointcut : " + Arrays.toString(joinPoint.getArgs()));
		try {
			System.out.println("@Around Advice : Now proceeding...");
			retValue = joinPoint.proceed();
			System.out.println("@Around Advice : Proceeding Finished !");
		} catch (Throwable e) {
			System.out.println("@Around Advice : Some error occured : " + e);
		}
		System.out.println("@Around Advice : end\n\n");
		
		
		return retValue;
	}
	
	
	
	private void accessTargetClass(JoinPoint joinPoint) {
		Integer age = ((TargetClass)joinPoint.getTarget()).getAge();
		String name = ((TargetClass)joinPoint.getTarget()).getName();
		System.out.println("	accessTargetClass:Age=["+age+"]");
		System.out.println("	accessTargetClass:Name=["+name+"]");
		System.out.println("	accessTargetClass:Resetting Age=["+(age+2)+"]");
		((TargetClass)joinPoint.getTarget()).setAge(age+2);
	}
	

}
