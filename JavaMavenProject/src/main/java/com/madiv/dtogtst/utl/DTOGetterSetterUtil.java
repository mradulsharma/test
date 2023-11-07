package com.madiv.dtogtst.utl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.madiv.dtogtst.bsns.ColumnType;
import com.madiv.generic.util.StringUtil;

public class DTOGetterSetterUtil extends StringUtil {
    public static ColumnType getColumnType(String line){
        ColumnType columnType = null;
        if(!isEmpty(line)){
            String value = line.substring(4, line.indexOf("("));
            columnType = findColumnType(value);
        }
        
        return columnType;
    } 
    
    private static ColumnType findColumnType(String columnType){
        ColumnType retValue = null;
        
        for (ColumnType type : ColumnType.values()){
            if(type.toString().equals(columnType)) retValue = type; 
        }
        
        return retValue;
    }

    public static String getField(String line) {
        String retValue = null;
        
        String lineRegex = "(.*)\"(.*)\"(.*)";
        Pattern pattern = Pattern.compile(lineRegex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String one = matcher.group(1);
            String two = matcher.group(2);
            String three = matcher.group(3);
            
            retValue = two;
        }        
        return retValue;
    }
    
    
    public static String getFieldCamelCase(String sourceLine) {
        StringBuffer buffer = new StringBuffer();
        boolean underScoreFound = false;
        String fieldName = getField(sourceLine);
        for(char c : fieldName.toCharArray()){
            
            if (c == '_'){
                underScoreFound = true;
                continue;
            }
            else{
                if(underScoreFound){
                    String s = "" + c;
                    buffer.append(s.toUpperCase());    
                    underScoreFound = false;
                } else {
                    buffer.append(c);    
                }
            }
        }
        
        return buffer.toString();
    }
    
}
