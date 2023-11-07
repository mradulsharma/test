package com.madiv.jar;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.madiv.generic.context.ApplicationContext;
import com.madiv.generic.context.Props;
import com.madiv.jar.bsns.JarType;
import com.madiv.jar.util.JarControllerUtil;

public class JarControllerUnitTest {

    @Test
    public void testUtil() {
        String filePath = "E:\\User\\Maddy\\P12-Sonata\\GitWorkspace\\base\\sonata\\sonata-dao\\src\\bravura\\sonata\\dao\\scheme\\setup\\superstream\\SuperstreamScheduleMemberData.java";
        
        
        assertEquals("sonata-dao", JarControllerUtil.findModuleName(filePath));
        assertEquals(JarType.sonata_dao, JarControllerUtil.getColumnType(filePath));
    }
}
