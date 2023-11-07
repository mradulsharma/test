package com.madiv.validate;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class NinoValidator {

    List<String> beginWithCharsSeq = Arrays.asList(new String[]{"BG", "GB", "KN", "NK", "NT", "TN", "ZZ"});
    String patttern = "^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$";
    
    
    @Test
    public void testNino() {

        NinoValidator validator = new NinoValidator();
        
        assertEquals("Valid", validator.checkNino("AA897432A"));    // Expected => True
        assertEquals("Valid", validator.checkNino("AA897432"));     // Expected => True (Should be OK, 8 character NINO allowed)
        assertEquals("Invalid", validator.checkNino(""));           // Expected => false (Blank not allowed)
        assertEquals("Valid", validator.checkNino(null));         // Expected => True (can be null)
        assertEquals("Invalid", validator.checkNino("ab897432a"));  // Expected => false (small chars not allowed)
        assertEquals("Invalid", validator.checkNino("AD897432A"));  // Expected => false (Second character can't be D)
        assertEquals("Invalid", validator.checkNino("AA897432G"));  // Expected => false (Last character can't be G)
        assertEquals("Invalid", validator.checkNino("BG897432A"));  // Expected => false (Can't begin with BG)
        assertEquals("Invalid", validator.checkNino("GB897432A"));  // Expected => false (Can't begin with GB)
        assertEquals("Invalid", validator.checkNino("KN897432A"));  // Expected => false (Can't begin with KN)
        assertEquals("Invalid", validator.checkNino("NK897432A"));  // Expected => false (Can't begin with NK)
        assertEquals("Invalid", validator.checkNino("NT897432A"));  // Expected => false (Can't begin with NT)
        assertEquals("Invalid", validator.checkNino("TN897432A"));  // Expected => false (Can't begin with TN)
        assertEquals("Invalid", validator.checkNino("ZZ897432A"));  // Expected => false (Can't begin with ZZ)

        assertEquals("Valid", validator.checkNino("ZA897432A"));    // Expected => True
        assertEquals("Valid", validator.checkNino("AZ897432A"));    // Expected => True
    
    }
    
 
    
    
    private String checkNino(String nino) {
        String checkResult = "Valid"; 

        if(!check(nino, p -> p == null)){
            if(
                    checkLength(nino.length(), p -> (p < 8 || p > 9 ))
                    ||
                    check(nino.trim(), p -> !p.matches(patttern))
                    ||
                    isBeginWith(nino, beginWithCharsSeq)
              ){
                checkResult = "Invalid";
            }
        }
        
        return checkResult;
    }
    
    private boolean isBeginWith(String nino, List<String> beginWithCharsSeq) {
        boolean retValue = false;
        for(String charSeq : beginWithCharsSeq){
            if(nino.startsWith(charSeq)){
                retValue = true;
                break;
            }
        }
        return retValue;
    }

    private boolean check(String nino, Predicate<String> rule){
        return rule.test(nino);
    }
    
    private boolean checkLength(Integer nino, Predicate<Integer> rule){
        return rule.test(nino);
    }
    
    
}
