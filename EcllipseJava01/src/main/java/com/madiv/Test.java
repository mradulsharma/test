package com.madiv;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
//             Do not delete, can be used for TDD 
        test.checkNino("AA897432A");       // Expected => True
        test.checkNino("AA897432");        // Expected => True (Should be ok, 8 character allowed)
        test.checkNino("AA897432 ");       // Expected => True (Should be ok, 9th character can be space)
        test.checkNino("");                // Expected => false (Blank not allowed)
        test.checkNino(null);              // Expected => True (can be null)
        test.checkNino("ab897432a");       // Expected => false (small chars not allowed)
        test.checkNino("AD897432A");       // Expected => false (Second character cant be D)
        test.checkNino("AA897432G");       // Expected => false (Last character cant be G)
        test.checkNino("BG897432A");       // Expected => false (Cant begin with BG)
        test.checkNino("GB897432A");       // Expected => false (Cant begin with GB)
        test.checkNino("KN897432A");       // Expected => false (Cant begin with KN)
        test.checkNino("NK897432A");       // Expected => false (Cant begin with NK)
        test.checkNino("NT897432A");       // Expected => false (Cant begin with NT)
        test.checkNino("TN897432A");       // Expected => false (Cant begin with TN)
        test.checkNino("ZZ897432A");       // Expected => false (Cant begin with ZZ)
        test.checkNino("ZA897432A");       // Expected => True
        test.checkNino("AZ897432A");       // Expected => True
        

        
//        String newName = test.renameAllMessageProperty("sonata-dao/src/bravura/sonata/dao/scheme/all-messages.properties");
//        String newName = test.renameAllMessageProperty("sonata-dao/src/bravura/sonata/dao/contribpayments/all-messages.properties");
//        System.out.println("newName =["+newName+"]");
        
        
    }

    private String renameAllMessageProperty(String fileName) {
        
        if(isPropertyFile(fileName)){
            String onlyFileName =  fileName.substring(fileName.lastIndexOf('/')+1);
            String remainingPath =  fileName.substring(0, fileName.lastIndexOf('/'));
            String previsouFolderName =  remainingPath.substring(remainingPath.lastIndexOf('/')+1);
            fileName = previsouFolderName + "_" + onlyFileName;
        }
        
        
        return fileName;
    }
    
    
    private boolean isPropertyFile(String file){
        return file.endsWith("all-messages.properties"); 
    }

    
    
    
    private void checkNino(String nino) {
        List<String> beginWithCharsSeq = Arrays.asList(new String[]{"BG", "GB", "KN", "NK", "NT", "TN", "ZZ"});
        String patttern = "^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$";
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
        
        
        System.out.println(String.format("NINO [%9s] %10s",nino, checkResult));
        
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
    
    // Do not delete, can be used for TDD 
    private void validateNino(String input) {
//        String patttern = "[A-z][A-z][0-9][0-9][0-9][0-9][0-9][0-9]([A-z]|[0-9])";
        
        String patttern = "^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$";
        boolean isValidNINO = (input == null)?false:input.trim().matches(patttern);
        
        if( 
                isValidNINO && (
                    input == null ||
                    input.startsWith("BG") ||
                    input.startsWith("GB") ||
                    input.startsWith("KN") ||
                    input.startsWith("NK") ||
                    input.startsWith("NT") ||
                    input.startsWith("TN") ||
                    input.startsWith("ZZ")
                )
        ){
            isValidNINO = false;
        }
        
        
        System.out.println("input=["+input+"], and mathching with pattern=["+patttern+"] = ["+isValidNINO+"]\n");
    }

}




class Testing{
    int age;
    String name;
    
    private int getAge() {
        return age;
    }
    
    private void setAge(int age) {
        this.age = age;
    }
    
    private String getName() {
        return name;
    }
    
    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Testing [age=" + age + ", name=" + name + "]";
    }
    
    
}
