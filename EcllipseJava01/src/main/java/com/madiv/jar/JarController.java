package com.madiv.jar;


public abstract class JarController {
    private String[] fileList = {
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EmploymentV.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\account\\maintenance\\relationships\\EarningfrequencyDD.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\account\\maintenance\\relationships\\EarningtypeDD.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningDO.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningDW.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningargsDO.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningargsDW.java"
    }; 
    
    
    public void printList(){
        for(String file:fileList){
            System.out.println(file);
        }
    }


    public void setFileList(String[] fileList2) {
        this.fileList = fileList2;
    }
}
