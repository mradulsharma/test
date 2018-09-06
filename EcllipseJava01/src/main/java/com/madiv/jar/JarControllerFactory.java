package com.madiv.jar;


public class JarControllerFactory {
    
    private static JarPBXController pbxController = null;
    

    public static JarController getController(JarType pbx) {
        
        JarController controller = null;
        
        switch (pbx) {
            case PBX:
                if (pbxController == null) pbxController = new JarPBXController();
                controller = pbxController;
                break;

            case DAO:
                break;
                
        }
        
        
        return controller;
    }

}
