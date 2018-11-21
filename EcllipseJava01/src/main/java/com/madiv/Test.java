package com.madiv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.doIt();
        
        
    }

	private void doIt() {
		String line1 = "NewEmpPostQ:PostQuestionnairePrepareOffer?"; 
//		String line2 = "LiteNewEmp:PrepareOffer?";
		
		
        String step = StringUtils.substringBetween(line1, ":", "?");
        step = StringUtils.trimToEmpty(step);
        
        String workflowStepCriteria = "PrepareOffer|Deleted";
        if (containsAny(step, workflowStepCriteria))System.out.println("Found It !!");
        else System.out.println("Nope !!");
        
		
		
		
	}

	private boolean containsAny(String stepFromDatabase, String workflowStepCriteria) {
		boolean retValue = false;
		
		String[] allSelectedSteps = workflowStepCriteria.split(Pattern.quote("|"));
		
		for(String step : allSelectedSteps) {
			if(stepFromDatabase.contains(step)) {
				retValue = true;
				break;
			}
		}
		
		return retValue;
	}

}
    
    
    
    



