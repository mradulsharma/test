package com.madiv.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.madiv.spring.aop.bean")
@EnableAspectJAutoProxy
public class AOPConfig {
	
}
