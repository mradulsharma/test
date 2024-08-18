package com.madiv;

public class RuntimeEnvironment {
    public static void main(String[] args) {
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java Runtime Version: " + javaVersion);
//        Java Runtime Version: 17.0.9
        
        String javaHome = System.getProperty("java.home");
        System.out.println("Java Installation Directory: " + javaHome);      
//        Java Installation Directory: C:\Users\maddy\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.9.v20231028-0858\jre        
    }
}