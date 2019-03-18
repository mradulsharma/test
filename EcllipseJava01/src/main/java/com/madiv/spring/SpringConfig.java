package com.madiv.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.madiv.spring.bean.Employee;
import com.madiv.spring.bean.EmployeeImp;

@Configuration
@ComponentScan(basePackages = "com.madiv.spring.bean")
public class SpringConfig {
	   @Bean
	   public Employee getEmployee() {
	      return new EmployeeImp();
	   }
}
