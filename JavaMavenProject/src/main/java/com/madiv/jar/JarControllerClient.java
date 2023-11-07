package com.madiv.jar;


/*
 * Generates commands for batch file to replace files in JAR file.
 */
        
public class JarControllerClient {
    private String[] fileList = {
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EmploymentV.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\account\\maintenance\\relationships\\EarningfrequencyDD.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\account\\maintenance\\relationships\\EarningtypeDD.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningDO.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningDW.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningargsDO.java",
            "e:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-pbx\\src\\bravura\\sonata\\common\\employment\\EarningargsDW.java"
    }; 
    
    
    
    public static void main(String[] args) {
        
        JarControllerClient client = new JarControllerClient();
        client.doIt();
        
        
        
    }



    private void doIt() {
        JarController controller = JarControllerFactory.getController(JarType.PBX);
        controller.setFileList(fileList);
        
        
        controller.printList();
    }
}
