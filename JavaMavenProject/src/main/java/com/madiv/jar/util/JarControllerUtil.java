package com.madiv.jar.util;

import java.io.File;

import com.madiv.generic.context.ApplicationContext;
import com.madiv.generic.context.Props;
import com.madiv.generic.util.StringUtil;
import com.madiv.jar.bsns.JarType;

public class JarControllerUtil extends StringUtil {
    public static JarType getColumnType(String filePath){
        
        String sonataModuleName = findModuleName(filePath);
        
        sonataModuleName = sonataModuleName.replace('-', '_');
        
        return JarType.valueOf(sonataModuleName);
    } 
    

    public static String findModuleName(String filePath){
        String moduleName = null;
        ApplicationContext.getProperty(Props.JAR_ECLIPSE_PROJECT_PATH);
        if (filePath == null || (filePath != null && filePath.indexOf(ApplicationContext.getProperty(Props.JAR_ECLIPSE_PROJECT_PATH)) != 0)){
            return null;
        }
        
        String beginPath = ApplicationContext.getProperty(Props.JAR_ECLIPSE_PROJECT_PATH) + File.separator + ApplicationContext.getProperty(Props.JAR_GIT_PROJECT_SOANTA);
        moduleName = filePath.substring( beginPath.length() + 1,
                            filePath.indexOf('\\', beginPath.length()+1)
                            );
        
        return moduleName;
    } 

    
    
}
