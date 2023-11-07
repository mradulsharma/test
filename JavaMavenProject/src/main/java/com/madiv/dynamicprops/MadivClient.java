package com.madiv.dynamicprops;

import java.util.Date;

public class MadivClient {
    public static void main(String[] args) throws InterruptedException {
        
        for(int i=1;i <=100; i++){
            if(i%10 == 0) {
                Thread.sleep(1000l);
                System.out.println((new Date()).getTime()+"\n\n");
                continue;
            }
            System.out.println(PropertyFileReader.getPropertyKeyValue("com.madiv.dynamicprops.testfile", "Key"+i%10));
            
            
        }
        
        
        
    }
}
