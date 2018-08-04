package com.madiv.validate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecimalNumberValdiator {
    private String patttern = "^[0-9]{0,12}(\\.[0-9]+){0,1}$";
    
    @Test
    public void testRun() {
        DecimalNumberValdiator validiator = new DecimalNumberValdiator();
        
        assertEquals(true, validiator.validateDcimal(null));                        //                Number [null                ] => Valid
        assertEquals(true, validiator.validateDcimal(""));                          //                Number [empty-str           ] => Valid
        assertEquals(false, validiator.validateDcimal(" "));                        //                Number [white-space         ] => InValid
        assertEquals(false, validiator.validateDcimal("."));                        //                Number [.                   ] => InValid
        assertEquals(true, validiator.validateDcimal("1"));                         //                Number [1                   ] => Valid
        assertEquals(false, validiator.validateDcimal("1."));                       //                Number [1.                  ] => InValid
        assertEquals(true, validiator.validateDcimal("1.1"));                       //                Number [1.1                 ] => Valid
        assertEquals(true, validiator.validateDcimal("123456789012.1"));            //                Number [123456789012.1      ] => Valid
        assertEquals(false, validiator.validateDcimal("1234567890123.1"));          //                Number [1234567890123.1     ] => InValid
        assertEquals(false, validiator.validateDcimal("123456789012."));            //                Number [123456789012.       ] => InValid
        assertEquals(true, validiator.validateDcimal("123456789012.12"));           //                Number [123456789012.12     ] => Valid
        assertEquals(true, validiator.validateDcimal("123456789012.123"));          //                Number [123456789012.123    ] => Valid
        assertEquals(false, validiator.validateDcimal("123456789012.123."));        //                Number [123456789012.123.   ] => InValid
        assertEquals(false, validiator.validateDcimal("123456789012.123.12"));      //                Number [123456789012.123.12 ] => InValid
    }
    
    
    private boolean validateDcimal(String number) {
        boolean retValue = false;
        
        if(number == null) retValue = true;
        else retValue = number.matches(patttern);
        
        return retValue;
    }


    private void validateAndPrint(String number) {
        String patttern = "^[0-9]{0,12}(\\.[0-9]+){0,1}$";
        
        if(number == null) System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "Valid"));
        else if(number.matches(patttern))System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "Valid !!"));
        else System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "InValid"));
        
        
    }

    private Object getNumber(String number) {
        String retValue=null;
        
        if(number == "")retValue = "empty-str";
        else if(number == " ")retValue = "white-space";
        else retValue = number;
        
        return retValue;
    }
    
}
