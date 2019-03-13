package com.madiv.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.madiv.dtogtst.in.HardCodedInput;
import com.madiv.dtogtst.in.Input;

public class RegexTest {
    public static void main(String[] args) {
    	RegexTest again = new RegexTest();
        again.doRegEx();
     
 }

 private void start() {
     Input in = new HardCodedInput();
     System.out.println("totalRecord=["+in.getTotalRecords()+"]");
     System.out.println("columnType=["+in.getColumnType(2)+"]");
     System.out.println("columnType=["+in.getColumnType(14)+"]");
     System.out.println("columnType=["+in.getColumnType(15)+"]");
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
}
