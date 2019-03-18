package com.madiv.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Company {

   private Employee employee;

   // Constructor based DI
   @Autowired
   public Company(Employee employee) {
      this.employee = employee;
   }

   public void showEmployeeInfo() {
      employee.showEmployeeInfo();
   }

}