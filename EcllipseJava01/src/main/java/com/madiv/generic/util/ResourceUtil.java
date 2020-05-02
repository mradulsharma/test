package com.madiv.generic.util;

import java.io.File;
import java.net.URL;

import com.madiv.rest.RestAPIServiceUtil;

public class ResourceUtil {
	// get file from classpath, resources folder
    public static File getFileFromResources(String fileName) {

        ClassLoader classLoader = RestAPIServiceUtil.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File \""+fileName+"\" is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

}
