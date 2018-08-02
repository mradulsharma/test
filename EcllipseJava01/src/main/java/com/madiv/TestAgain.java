package com.madiv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.madiv.dtogtst.in.HardCodedInput;
import com.madiv.dtogtst.in.Input;

public class TestAgain{
    public static void main(String[] args) {
           TestAgain again = new TestAgain();
//           again.start();
           again.me1();
//           again.doRegEx();
//           again.doRegExMultipleMatch();
//           again.doRegExCamelCase();
//           again.doUpperCase();
        
        
//        String test = "new LongColumnMetaData(\"spmd_cont_1_rule_amt\"),";
//        String value = test.substring(4, test.indexOf("("));
//        System.out.println("Value=["+value+"]");
        
    }

    private void me1() {
        
        for(int i=0; i<2; i++){
            String fieldValue = null;
            
            if(!isEmpty(fieldValue = getA())){
                System.out.println("fieldValue.getA("+i+")=["+fieldValue+"]");
            } else {
                System.out.println("ELSE fieldValue.getA("+i+")=["+fieldValue+"]");
            }
            
            if(!isEmpty(fieldValue = getB())){
                System.out.println("fieldValue.getB("+i+")=["+fieldValue+"]");
            } else {
                System.out.println("ELSE fieldValue.getB("+i+")=["+fieldValue+"]");
            }
            
            if(!isEmpty(fieldValue = getC())){
                System.out.println("fieldValue.getC("+i+")=["+fieldValue+"]");
            } else {
                System.out.println("ELSE fieldValue.getC("+i+")=["+fieldValue+"]");
            }

            System.out.println();
        }
        
         
    }

    private boolean isEmpty(String string) {
        return string == null;
    }

    private String getC() {
        return "C";
    }

    private String getB() {
        return null;
    }

    private String getA() {
        return "A";
    }

    private void start() {
        Input in = new HardCodedInput();
        System.out.println("totalRecord=["+in.getTotalRecords()+"]");
        System.out.println("columnType=["+in.getColumnType(2)+"]");
        System.out.println("columnType=["+in.getColumnType(14)+"]");
        System.out.println("columnType=["+in.getColumnType(15)+"]");
        
        
//        System.out.println("fieldName=["+in.getField(0)+"]");
//        System.out.println("fieldName=["+in.getField(1)+"]");
//        System.out.println("fieldName=["+in.getField(12)+"]");
//
//        System.out.println("fieldCamelCase=["+in.getFieldCamelCase(0)+"]");
//        System.out.println("fieldCamelCase=["+in.getFieldCamelCase(1)+"]");
//        System.out.println("fieldCamelCase=["+in.getFieldCamelCase(12)+"]");
        
    }
    
    
    private void doRegEx(){
        String line = "new LongColumnMetaData(\"spmd_cont_1_rule_amt\"),";
        String lineRegex = "(.*)\"(.*)\"(.*)";
        Pattern pattern = Pattern.compile(lineRegex);
        Matcher matcher = pattern.matcher(line);
        System.out.println("Trying to matche regex ["+lineRegex+"]");
        if (matcher.matches()) {
            System.out.println("Matched");
            String one = matcher.group(1);
            String two = matcher.group(2);
            String three = matcher.group(3);
            System.out.println("one["+one+"], two["+two+"], three["+three+"]");
        }else{
            System.out.println("Not Matched !!");
        }        
    }
    
    
    private void doRegExMultipleMatch(){
        String line = "My name is Maddy, sometimes my name is Mradul, and my official name is Mradulanand.. its up to you what you want to call me";
        String lineRegex = "name is (.*?)[,.]";
        Pattern pattern = Pattern.compile(lineRegex);
        Matcher matcher = pattern.matcher(line);
        System.out.println("Trying to matche regex ["+lineRegex+"]");
        while(matcher.find()) {
            String one = matcher.group(1);
            System.out.println("Matched=["+one+"]");
        }
    }
    
    
    
    

    
    
}



