package com.madiv.dtogtst.bsns;

import java.util.ArrayList;
import java.util.List;

import com.madiv.dtogtst.utl.DTOGetterSetterUtil;

public abstract class ColumnHandlerType {
    
    private String[] columnDefinition = null;

    public List<String> generateColumnDefinition(String columnDeclarationString, int beginIndex) {
        List<String> retValue = new ArrayList<String>();

        String firstUpperFieldName =
                DTOGetterSetterUtil.firstUpper(DTOGetterSetterUtil.getFieldCamelCase(columnDeclarationString));

        retValue.add(columnDefinition[0]);
        retValue.add(String.format(columnDefinition[1], firstUpperFieldName));
        retValue.add(String.format(columnDefinition[2], beginIndex));
        retValue.add(columnDefinition[3]);
        retValue.add(columnDefinition[4]);
        retValue.add(String.format(columnDefinition[5], firstUpperFieldName));
        retValue.add(String.format(columnDefinition[6], beginIndex));
        retValue.add(columnDefinition[7]);
        retValue.add(columnDefinition[8]);
        retValue.add(String.format(columnDefinition[9], firstUpperFieldName));
        retValue.add(String.format(columnDefinition[10], beginIndex, beginIndex));
        retValue.add(columnDefinition[11]);
        retValue.add(columnDefinition[12]);

        return retValue;
    }

    
    public void setColumnDefinition(String[] columnDefinition) {
        this.columnDefinition = columnDefinition;
    }
    
    
    
}
