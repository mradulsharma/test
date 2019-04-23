package com.madiv.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		RegexTest again = new RegexTest();
		System.out.println("Search method name, parameter call using reg-ex");
		again.doRegEx();
		
		
		System.out.println("\n\n\nGreey regex search...");
		again.doGreedyRegEx();


	}

	private void doRegEx() {
		String line = "new LongColumnMetaData(\"spmd_cont_1_rule_amt\"),";
		String lineRegex = "(.*)\"(.*)\"(.*)";
		Pattern pattern = Pattern.compile(lineRegex);
		Matcher matcher = pattern.matcher(line);
		System.out.println("Line to search on => " + line);
		System.out.println("regex =>" + lineRegex+"\n\n");
		if (matcher.matches()) {
			System.out.println("Matched");
			String one = matcher.group(1);
			String two = matcher.group(2);
			String three = matcher.group(3);
			System.out.println("First Group => \"" + one+"\"");
			System.out.println("Second Group => \"" + two+"\"");
			System.out.println("Third Group => \"" + three+"\"");
		} else {
			System.out.println("Not Matched !!");
		}
	}
	
	
    private void doGreedyRegEx(){
        String line = "My name is Maddy, sometimes my name is Mradul, and my official name is Mradulanand.. its up to you what you want to call me";
        String lineRegex = "name is (.*?)[,.]";
        Pattern pattern = Pattern.compile(lineRegex);
        Matcher matcher = pattern.matcher(line);
		System.out.println("Line to search on => " + line);
        System.out.println("regex => "+lineRegex);
        while(matcher.find()) {
            String one = matcher.group(1);
            System.out.println("Matched=["+one+"]");
        }
    }
	
}
