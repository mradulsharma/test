package com.madiv.validate;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class DecimalNumberValdiator {
    private String decimalFormatPatttern = "^[0-9]{0,12}(\\.[0-9]+){0,1}$";
    private String decimalRateFormatPatttern = "^[0-9]{0,3}(\\.[0-9]+){0,1}$";
    
    @Test
    public void testRunAmount() {
        DecimalNumberValdiator validiator = new DecimalNumberValdiator();
        
        assertEquals(false, validiator.parseDecimal(null));                        //                Number [null                ] => Valid
        assertEquals(false, validiator.parseDecimal(""));                          //                Number [empty-str           ] => Valid
        assertEquals(true, validiator.parseDecimal(" "));                          //                Number [white-space         ] => InValid
        assertEquals(true, validiator.parseDecimal("."));                          //                Number [.                   ] => InValid
        assertEquals(false, validiator.parseDecimal(".1"));                        //                Number [.1                  ] => Valid
        assertEquals(false, validiator.parseDecimal("1"));                         //                Number [1                   ] => Valid
        assertEquals(true, validiator.parseDecimal("1."));                         //                Number [1.                  ] => InValid
        assertEquals(false, validiator.parseDecimal("1.1"));                       //                Number [1.1                 ] => Valid
        assertEquals(false, validiator.parseDecimal("123456789012.1"));            //                Number [123456789012.1      ] => Valid
        assertEquals(true, validiator.parseDecimal("1234567890123.1"));            //                Number [1234567890123.1     ] => InValid
        assertEquals(true, validiator.parseDecimal("123456789012."));              //                Number [123456789012.       ] => InValid
        assertEquals(false, validiator.parseDecimal("123456789012.12"));           //                Number [123456789012.12     ] => Valid
        assertEquals(false, validiator.parseDecimal("123456789012.123"));          //                Number [123456789012.123    ] => Valid
        assertEquals(true, validiator.parseDecimal("123456789012.123."));          //                Number [123456789012.123.   ] => InValid
        assertEquals(true, validiator.parseDecimal("123456789012.123.12"));        //                Number [123456789012.123.12 ] => InValid
    }
    
    @Test
    public void testRunRate() {
        DecimalNumberValdiator validiator = new DecimalNumberValdiator();
        
        assertEquals(false, validiator.parseDecimalRate(null));                      //                Number [null                ] => Valid
        assertEquals(false, validiator.parseDecimalRate(""));                        //                Number [empty-str           ] => Valid
        assertEquals(true, validiator.parseDecimalRate(" "));                        //                Number [white-space         ] => InValid
        assertEquals(true, validiator.parseDecimalRate("."));                        //                Number [.                   ] => InValid
        assertEquals(false, validiator.parseDecimalRate(".1"));                      //                Number [.1                  ] => Valid
        assertEquals(false, validiator.parseDecimalRate("1"));                       //                Number [1                   ] => Valid
        assertEquals(true, validiator.parseDecimalRate("1."));                       //                Number [1.                  ] => InValid
        assertEquals(false, validiator.parseDecimalRate("1.1"));                     //                Number [1.1                 ] => Valid
        assertEquals(false, validiator.parseDecimalRate("12.1"));                    //                Number [12.1                ] => Valid
        assertEquals(false, validiator.parseDecimalRate("100.0"));                   //                Number [100.0               ] => Valid
        assertEquals(false, validiator.parseDecimalRate("100.00"));                  //                Number [100.00              ] => Valid
        assertEquals(true, validiator.parseDecimalRate("100.01"));                   //                Number [100.01              ] => InValid
        assertEquals(true, validiator.parseDecimalRate("100.1"));                    //                Number [100.1               ] => InValid
        assertEquals(true, validiator.parseDecimalRate("12.12."));                   //                Number [12.12.              ] => InValid
        assertEquals(true, validiator.parseDecimalRate("12.12.12"));                 //                Number [12.12.12            ] => InValid
        assertEquals(false, validiator.parseDecimalRate("0.0"));                     //                Number [0.0                 ] => Valid
        assertEquals(false, validiator.parseDecimalRate("00.0"));                    //                Number [00.0                ] => Valid
        assertEquals(false, validiator.parseDecimalRate("0.00"));                    //                Number [0.00                ] => Valid
        assertEquals(false, validiator.parseDecimalRate("00.00"));                   //                Number [00.00               ] => Valid
        assertEquals(false, validiator.parseDecimalRate("000.00"));                  //                Number [000.00              ] => Valid
        assertEquals(false, validiator.parseDecimalRate("000.000"));                 //                Number [000.000             ] => Valid (Framework will truncate extra decimal at right after 2)
        assertEquals(true, validiator.parseDecimalRate("000101"));                   //                Number [000101              ] => InValid
        assertEquals(true, validiator.parseDecimalRate("111111"));                   //                Number [111111              ] => InValid
    }
    
    
    
    private boolean parseDecimal(String fieldValue){
        boolean errorsFound = false;
        
        errorsFound = _parseDecimal(fieldValue, decimalFormatPatttern);
        
        return errorsFound;
    }
    
    private boolean parseDecimalRate(String fieldValue){
        boolean errorsFound = false;
        
        errorsFound = _parseDecimal(fieldValue, decimalRateFormatPatttern);
        if(!errorsFound && !isEmpty(fieldValue)) {
            errorsFound = ((new BigDecimal(fieldValue)).compareTo(new BigDecimal("100")) == 1)?true:false; 
        }
        
        return errorsFound;
    }
    
    
    
    private boolean _parseDecimal(String fieldValue, String pattern) {
        boolean errorsFound = false;

        if (!isEmpty(fieldValue)) {
            try {
                BigDecimal tempAmount = new BigDecimal(fieldValue);
            } catch (Exception e) {
                errorsFound = true;
            }
        }

        if (!errorsFound) { // If converted to BigDecimal, then parse against Pattern
            if (fieldValue == null) errorsFound = false;
            else errorsFound = !fieldValue.matches(pattern);
        }
            
       return errorsFound;
    }
    


    private void validateAndPrint(String number) {
        
        if(number == null) System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "Valid"));
        else if(number.matches(decimalFormatPatttern))System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "Valid !!"));
        else System.out.println(String.format("Number [%-20s] => %s", getNumber(number), "InValid"));
        
        
    }

    private Object getNumber(String number) {
        String retValue=null;
        
        if(number == "")retValue = "empty-str";
        else if(number == " ")retValue = "white-space";
        else retValue = number;
        
        return retValue;
    }
    
    
    public static boolean isEmpty(String s) {
        return isEmpty(s, true);
    }

    
    /**
     * Returns true if <code>s</code> is null or if <code>s</code> and has length of 0.
     */
    public static boolean isEmpty(String s, boolean ignoreWhite) {
        return (s == null || (0 == (ignoreWhite ? s.trim().length() : s.length())));
    }
    
}
